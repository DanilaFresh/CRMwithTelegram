package com.example.CRMforDelivery.entity.dto;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CourierResponseDto {
    @NotNull()
    @Size(max = 100)
    @NotBlank
    private String tgUserName;

    @NotNull()
    @Size(max = 30)
    @NotBlank
    private String name;

    @NotNull()
    @Size(max = 30)
    @NotBlank
    private String surname;

    @Size(max = 30)
    @NotBlank
    private String last_name;

    @NotNull()
    @Pattern(regexp = "^(\\+375|80)\\d{2}\\s?\\d{3}-\\d{2}-\\d{2}$",
    message = "phone number must be if form +375XX XXX-XX-XX or 80+375XX XXX-XX-XX")
    private String phone_number;

    public CourierResponseDto(String tgUserName, String name, String surname, String last_name, String phone_number) {
        this.tgUserName = tgUserName;
        this.name = name;
        this.surname = surname;
        this.last_name = last_name;
        this.phone_number = phone_number;
    }

    public CourierResponseDto() {
    }


}
