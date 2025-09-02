package com.example.CRMforDelivery.security.jwt;

public record Tokens(String accessToken,
                     String accessTokenExpiry,
                     String refreshToken,
                     String refreshTokenExpiry
                     ) {
}
