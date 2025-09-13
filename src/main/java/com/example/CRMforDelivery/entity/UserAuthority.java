package com.example.CRMforDelivery.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="user_authorities")
public class UserAuthority {

    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "authority", nullable = false)
    private String authority;

    public UserAuthority() {}

    public UserAuthority(User user, String authority) {
        this.user = user;
        this.authority = authority;
    }

}
