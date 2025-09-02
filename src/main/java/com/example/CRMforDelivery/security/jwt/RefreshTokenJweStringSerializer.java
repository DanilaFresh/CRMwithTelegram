package com.example.CRMforDelivery.security.jwt;

import com.nimbusds.jose.*;
import com.nimbusds.jwt.EncryptedJWT;
import com.nimbusds.jwt.JWTClaimsSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.function.Function;

public class RefreshTokenJweStringSerializer implements Function<Token, String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RefreshTokenJweStringSerializer.class);

    private final JWEEncrypter jweEncrypter;

    private JWEAlgorithm jweAlgorithm =
            JWEAlgorithm.DIR;

    public RefreshTokenJweStringSerializer(JWEEncrypter jweEncrypter) {
        this.jweEncrypter = jweEncrypter;
    }

    private EncryptionMethod encryptionMethod =
            EncryptionMethod.A128GCM;


    public RefreshTokenJweStringSerializer(JWEEncrypter jweEncrypter,
                                           JWEAlgorithm jweAlgorithm,
                                           EncryptionMethod encryptionMethod) {
        this.jweEncrypter = jweEncrypter;
        this.jweAlgorithm = jweAlgorithm;
        this.encryptionMethod = encryptionMethod;
    }


    @Override
    public String apply(Token token) {
            var jweHeader = new JWEHeader.Builder(this.jweAlgorithm, this.encryptionMethod)
                .keyID(token.id().toString())
                .build();
        var jwtClaimsSet = new JWTClaimsSet.Builder()
                .jwtID(token.id().toString())
                .subject(token.subject())
                .issueTime(Date.from(token.createdAt()))
                .expirationTime(Date.from(token.expiresAt()))
                .claim("authorities", token.authorities())
                .build();
        var encryptedJWT = new EncryptedJWT(jweHeader, jwtClaimsSet);
        try {
            encryptedJWT.encrypt(this.jweEncrypter);
            return encryptedJWT.serialize();
        } catch (JOSEException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }
}
