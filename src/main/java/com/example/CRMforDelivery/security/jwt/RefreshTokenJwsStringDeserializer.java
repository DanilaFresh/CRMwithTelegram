package com.example.CRMforDelivery.security.jwt;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEDecrypter;
import com.nimbusds.jwt.EncryptedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.UUID;
import java.util.function.Function;

public class RefreshTokenJwsStringDeserializer implements Function<String, Token> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RefreshTokenJwsStringDeserializer.class);

    private final JWEDecrypter jweDecrypter;

    public RefreshTokenJwsStringDeserializer(JWEDecrypter jweDecrypter) {
        this.jweDecrypter = jweDecrypter;
    }


    @Override
    public Token apply(String string) {
        try {
            var encryptedJWT = EncryptedJWT.parse(string);
            encryptedJWT.decrypt(this.jweDecrypter);
                var claimSet = encryptedJWT.getJWTClaimsSet();
                return new Token(
                        UUID.fromString(encryptedJWT.getJWTClaimsSet().getJWTID()),
                        claimSet.getSubject(),
                        claimSet.getStringListClaim("authorities"),
                        claimSet.getIssueTime().toInstant(),
                        claimSet.getExpirationTime().toInstant()
                );
        } catch (ParseException | JOSEException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }
}
