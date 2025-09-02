package com.example.CRMforDelivery;

import com.example.CRMforDelivery.security.jwt.*;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.crypto.DirectDecrypter;
import com.nimbusds.jose.crypto.DirectEncrypter;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jose.jwk.OctetSequenceKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

import java.text.ParseException;
import java.util.Objects;


@SpringBootApplication
public class CrMforDeliveryApplication {

    public static void main(String[] args) {

        SpringApplication.run(CrMforDeliveryApplication.class, args);
    }

    @Bean
    public JwtAuthConfigurer jwtAuthConfigurer(
            @Value("${jwt.access-token-key}") String accessTokenKey,
            @Value("${jwt.refresh-token-key}") String refreshTokenKey,
            JdbcTemplate jdbcTemplate)
            throws ParseException, JOSEException {
        return new JwtAuthConfigurer()
                .accessTokenStringSerializer(new AccessTokenJwsStringSerializer(
                        new MACSigner(OctetSequenceKey.parse(accessTokenKey))
                ))
                .refreshTokenStringSerializer(new RefreshTokenJweStringSerializer(
                        new DirectEncrypter(OctetSequenceKey.parse(refreshTokenKey))

                ))
                .accessTokenStringDeserializer(new AccessTokenJwsStringDeserializer(
                        new MACVerifier(OctetSequenceKey.parse(accessTokenKey))
                ))
                .refreshTokenStringDeserializer(new RefreshTokenJwsStringDeserializer(
                        new DirectDecrypter(OctetSequenceKey.parse(refreshTokenKey))
                ))
                .jdbcTemplate(jdbcTemplate);

    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity httpSecurity,
            JwtAuthConfigurer jwtAuthConfigurer) throws Exception {
        httpSecurity.apply(jwtAuthConfigurer);
        return httpSecurity.httpBasic(Customizer.withDefaults())
                .sessionManagement(sm ->
                        sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/manager.html").hasRole("MANAGER")
                                .requestMatchers("/error").permitAll()
                                .anyRequest().authenticated()
                )
                .build();
    }
    @Bean
    public UserDetailsService userDetailsService(JdbcTemplate jdbcTemplate) {
        return username -> Objects.requireNonNull(jdbcTemplate.query("select * from t_user where c_username = ?",
                (rs, i) -> User.builder()
                        .username(rs.getString("c_username"))
                        .password(rs.getString("c_password"))
                        .authorities(
                                jdbcTemplate.query("select c_authority from t_user_authority where id_user = ?",
                                        (rs1, i1) ->
                                                new SimpleGrantedAuthority(rs1.getString("c_authority")),
                                        rs.getInt("id")))
                        .build(), username).stream().findFirst().orElse(null));
    }
}
