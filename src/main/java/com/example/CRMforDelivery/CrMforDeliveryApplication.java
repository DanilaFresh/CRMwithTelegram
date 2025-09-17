package com.example.CRMforDelivery;

import com.example.CRMforDelivery.entity.User;
import com.example.CRMforDelivery.entity.UserAuthority;
import com.example.CRMforDelivery.repository.UserAuthorityRepository;
import com.example.CRMforDelivery.repository.UserRepository;
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
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;


@SpringBootApplication
public class CrMforDeliveryApplication {

    public static void main(String[] args) {

        SpringApplication.run(CrMforDeliveryApplication.class, args);
    }

}
