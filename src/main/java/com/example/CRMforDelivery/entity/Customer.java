package com.example.CRMforDelivery.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "customers")
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "surname", nullable = false, length = 30)
    private String surname;

    @Column(name = "last_name", length = 30)
    private String last_name;

    @Column(name = "phone_number", nullable = false, length = 15)
    private String phone_number;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();


    public Customer() {
    }

    public Customer(String name, String surname, String last_name, String phone_number) {
        this.name = name;
        this.surname = surname;
        this.last_name = last_name;
        this.phone_number = phone_number;
    }

}
