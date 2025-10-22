package com.example.CRMforDelivery.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="couriers")
public class Courier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name ="tg_user_name",nullable = false,unique = true)
    private String tgUserName;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "surname", nullable = false, length = 30)
    private String surname;

    @Column(name = "last_name",length = 30)
    private String last_name;


    @Column(name = "phone_number", nullable = false, length = 15)
    private String phone_number;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public Courier() {
    }

    public Courier(String name, String surname, String last_name, String phone_number) {
        this.name = name;
        this.surname = surname;
        this.last_name = last_name;
        this.phone_number = phone_number;
    }

}
