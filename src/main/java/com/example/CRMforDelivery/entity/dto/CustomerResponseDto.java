package com.example.CRMforDelivery.entity.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
public record CustomerResponseDto(

        @Size(max = 30)
        @NotBlank
        String name,

        @Size(max = 30)
        @NotBlank
        String surname,

        @Size(max = 30)
        String last_name,

        @NotNull(message = "phone_number can't be null")
        @Pattern(regexp = "^(\\+375|80)\\d{2}\\s?\\d{3}-\\d{2}-\\d{2}$",
                message = "phone number must be if form (+375|80)XXXXX-XX-XX ")
        String phone_number

) {
}
