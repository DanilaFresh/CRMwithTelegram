package com.example.CRMforDelivery.entity;

import jakarta.persistence.*;

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
    @JoinColumn(name = "customer_id",nullable = false)
    private Customer customer;

    @Column(name = "address", nullable = false, length = 100)
    private String address;

    @Column(name = "cargo_description", nullable = false, length = 500)
    private String cargoDescription;

    @Column(name = "delivery_wishes", length = 500)
    private String  deliveryWishes;

    public Order() {
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
