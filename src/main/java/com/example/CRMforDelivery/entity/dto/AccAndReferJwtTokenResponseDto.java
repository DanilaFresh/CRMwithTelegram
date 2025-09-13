package com.example.CRMforDelivery.entity.dto;

public record AccAndReferJwtTokenResponseDto
        (String accessToken,
         String accessTokenExpiry,
         String refreshToken,
         String refreshTokenExpiry
        ) {
}
