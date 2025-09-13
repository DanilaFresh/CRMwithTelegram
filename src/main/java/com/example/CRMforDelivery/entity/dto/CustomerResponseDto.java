package com.example.CRMforDelivery.entity.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerResponseDto {
    @NotNull()
    @Size(max = 30)
    @NotBlank
    private String name;

    @NotNull()
    @Size(max = 30)
    @NotBlank
    private String surname;

    @Size(max = 30)
    private String last_name;

    @NotNull(message = "phone_number can't be null")
    @Pattern(regexp = "^(\\+375|80)\\d{2}\\s?\\d{3}-\\d{2}-\\d{2}$",
    message = "phone number must be if form (+375|80)XXXXX-XX-XX ")
    private String phone_number;

    public CustomerResponseDto(String name, String surname, String last_name, String phone_number) {
        this.name = name;
        this.surname = surname;
        this.last_name = last_name;
        this.phone_number = phone_number;
    }

}
