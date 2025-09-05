package com.example.CRMforDelivery.entity;

import jakarta.persistence.*;


@Entity
@Table(name="user_authorities")
public class UserAuthority {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
