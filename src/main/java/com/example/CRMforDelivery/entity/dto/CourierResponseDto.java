package com.example.CRMforDelivery.entity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CourierResponseDto(

        @Size(max = 100)
        @NotBlank
        String tgUserName,

        @Size(max = 30)
        @NotBlank
        String name,

        @Size(max = 30)
        @NotBlank
        String surname,

        @Size(max = 30)
        @NotBlank
        String last_name,

        @NotNull()
        @Pattern(regexp = "^(\\+375|80)\\d{2}\\s?\\d{3}-\\d{2}-\\d{2}$",
                message = "phone number must be if form +375XX XXX-XX-XX or 80+375XX XXX-XX-XX")
        String phone_number
) {

}

