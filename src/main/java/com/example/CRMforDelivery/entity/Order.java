package com.example.CRMforDelivery.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "status", nullable = false, length = 100)
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "address", nullable = false, length = 100)
    private String address;

    @Column(name = "cargo_description", nullable = false, length = 500)
    private String cargoDescription;

    @Column(name = "delivery_wishes", length = 500)
    private String deliveryWishes;

    public Order() {
    }


    public Order(OrderStatus status,
                 Customer customer,
                 String address,
                 String cargoDescription,
                 String deliveryWishes) {
        this.status = status;
        this.customer = customer;
        this.address = address;
        this.cargoDescription = cargoDescription;
        this.deliveryWishes = deliveryWishes;
    }

}
