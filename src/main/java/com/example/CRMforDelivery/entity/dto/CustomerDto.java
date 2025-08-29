package com.example.CRMforDelivery.entity.dto;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.NumberFormat;

public class CustomerDto {
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

    public CustomerDto(String name, String surname, String last_name, String phone_number) {
        this.name = name;
        this.surname = surname;
        this.last_name = last_name;
        this.phone_number = phone_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
}
