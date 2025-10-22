package com.example.CRMforDelivery.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "Сущность курьера")
public record CourierResponseDto(

        @Schema(description = "username из Telegram")
        @Size(max = 100)
        @NotBlank
        String tgUserName,

        @Schema(description = "имя")
        @Size(max = 30)
        @NotBlank
        String name,

        @Schema(description = "фамилия")
        @Size(max = 30)
        @NotBlank
        String surname,

        @Schema(description = "отчество")
        @Size(max = 30)
        @NotBlank
        String last_name,

        
        @Schema(description = "телефон",example = "+375XX XXX-XX-XX или 80XX XXX-XX-XX ")
        @NotNull()
        @Pattern(regexp = "^(\\+375|80)\\d{2}\\s?\\d{3}-\\d{2}-\\d{2}$",
                message = "phone number must be if form +375XX XXX-XX-XX or 80XX XXX-XX-XX")
        String phone_number
) {

}

