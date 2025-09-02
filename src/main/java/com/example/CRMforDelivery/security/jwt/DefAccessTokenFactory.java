package com.example.CRMforDelivery.security.jwt;

import java.time.Duration;
import java.time.Instant;
import java.util.function.Function;

public class DefAccessTokenFactory implements Function<Token,Token> {
    public Duration tokenTtl=Duration.ofMinutes(5);
    @Override
    public Token apply(Token token) {
        var now= Instant.now();
        return new Token(token.id(),
                token.subject(),
                token.authorities().stream()
                        .filter(authority->authority.startsWith("GRANT_"))
                        .map(authority->authority.replaceAll("GRANT_",""))
                        .toList(),
                now,
                now.plus(this.tokenTtl)
        );
    }
}
