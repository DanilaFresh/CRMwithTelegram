package com.example.CRMforDelivery.entity.dto;

import com.example.CRMforDelivery.entity.OrderStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderRequestDto {

    private long customerId;

    @NotNull
    private OrderStatus status;

    @NotNull()
    @Size(max = 100)
    @NotBlank
    private String address;

    @NotNull()
    @Size(max = 500)
    @NotBlank
    private String cargoDescription;

    @Size(max = 500)
    private String deliveryWishes;

    public OrderRequestDto(long customerId,
                           OrderStatus status,
                           String address,
                           String cargoDescription,
                           String deliveryWishes) {
        this.customerId = customerId;
        this.status = status;
        this.address = address;
        this.cargoDescription = cargoDescription;
        this.deliveryWishes = deliveryWishes;
    }

    public OrderRequestDto() {
    }


}
