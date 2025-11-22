package com.example.CRMforDelivery.security.jwt;

import com.example.CRMforDelivery.repository.DeactivatedTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;


import java.util.function.Function;

public class JwtAuthConfigurer extends AbstractHttpConfigurer<JwtAuthConfigurer, HttpSecurity> {
    @Autowired
    private DeactivatedTokenRepository tokenRepository;

    private Function<Token, String> refreshTokenStringSerializer = Object::toString;

    private Function<Token, String> accessTokenStringSerializer = Object::toString;

    private Function<String, Token> accessTokenStringDeserializer;

    private Function<String, Token> refreshTokenStringDeserializer;


    @Override
    public void init(HttpSecurity builder)  {
        var csrfConfigurer = builder.getConfigurer(CsrfConfigurer.class);

        if (csrfConfigurer != null) {
            //TODO сделать csrf защиту
            csrfConfigurer.disable();
             csrfConfigurer.ignoringRequestMatchers(
                    PathPatternRequestMatcher.pathPattern( HttpMethod.POST,"/jwt/tokens"));
        }


    }

    @Override
    public void configure(HttpSecurity builder)  {
        var requestJwtTokensFilter = new RequestJwtTokensFilter();
        requestJwtTokensFilter.setRefreshTokenStringSerializer(this.refreshTokenStringSerializer);
        requestJwtTokensFilter.setAccessTokenStringSerializer(this.accessTokenStringSerializer);

        var jwtAuthFilter = new AuthenticationFilter(
                builder.getSharedObject(AuthenticationManager.class),
                new JwtAuthConverter(this.accessTokenStringDeserializer,
                        this.refreshTokenStringDeserializer));
        var authenticationProvider = new PreAuthenticatedAuthenticationProvider();
        authenticationProvider.setPreAuthenticatedUserDetailsService(
                new TokenAuthUserDetailService(tokenRepository));

        jwtAuthFilter.setSuccessHandler(
                (request, response, authentication) ->
                        CsrfFilter.skipRequest(request)
        );

        jwtAuthFilter.setFailureHandler((request, response, exception) ->
                response.sendError(HttpStatus.FORBIDDEN.value()));

        var refreshTokenFilter = new RefreshTokenFilter();
        refreshTokenFilter.setAccessTokenStringSerializer(this.accessTokenStringSerializer);

        var jwtLogoutFilter=new JwtLogoutFilter(tokenRepository);



        builder.addFilterAfter(requestJwtTokensFilter, ExceptionTranslationFilter.class)
                .addFilterBefore(jwtAuthFilter, CsrfFilter.class)
                .addFilterAfter(refreshTokenFilter, ExceptionTranslationFilter.class)
                .addFilterAfter(jwtLogoutFilter, ExceptionTranslationFilter.class)
                .authenticationProvider(authenticationProvider)
        ;
    }

    public JwtAuthConfigurer refreshTokenStringSerializer(Function<Token, String> refreshTokenStringSerializer) {
        Token token;
        this.refreshTokenStringSerializer = refreshTokenStringSerializer;
        return this;
    }

    public JwtAuthConfigurer accessTokenStringSerializer(Function<Token, String> accessTokenStringSerializer) {
        this.accessTokenStringSerializer = accessTokenStringSerializer;
        return this;
    }

    public JwtAuthConfigurer accessTokenStringDeserializer(Function<String, Token> accessTokenStringDeserializer) {
        this.accessTokenStringDeserializer = accessTokenStringDeserializer;
        return this;
    }

    public JwtAuthConfigurer refreshTokenStringDeserializer(Function<String, Token> refreshTokenStringDeserializer) {
        this.refreshTokenStringDeserializer = refreshTokenStringDeserializer;
        return this;
    }

}
