package com.example.CRMforDelivery.entity.dto;

import com.example.CRMforDelivery.entity.enums.OrderStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.NumberFormat;

public record OrderResponseDto(
        @NotNull
        @NumberFormat(style = NumberFormat.Style.NUMBER)
        long customerId,

        @NotNull
        OrderStatus status,

        @Size(max = 100)
        @NotBlank
        String address,

        @Size(max = 500)
        @NotBlank
        String cargoDescription,

        @Size(max = 500)
        String deliveryWishes

) {
}
