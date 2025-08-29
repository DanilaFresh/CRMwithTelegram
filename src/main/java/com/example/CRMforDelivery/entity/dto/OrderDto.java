package com.example.CRMforDelivery.entity.dto;

import com.example.CRMforDelivery.entity.Customer;
import com.example.CRMforDelivery.entity.OrderStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

public class OrderDto {

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

    public OrderDto(long customerId,
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

    public OrderDto() {
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customer_id) {
        this.customerId = customer_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getCargoDescription() {
        return cargoDescription;
    }

    public void setCargoDescription(String cargoDescription) {
        this.cargoDescription = cargoDescription;
    }

    public String getDeliveryWishes() {
        return deliveryWishes;
    }

    public void setDeliveryWishes(String deliveryWishes) {
        this.deliveryWishes = deliveryWishes;
    }


}
