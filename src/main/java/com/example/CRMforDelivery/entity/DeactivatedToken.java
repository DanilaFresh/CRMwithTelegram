package com.example.CRMforDelivery.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "deactivated_tokens")
public class DeactivatedToken {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Setter
    @Getter
    @Column(name = "c_keep_until", nullable = false)
    private Instant keepUntil;

    public DeactivatedToken() {
    }

    public DeactivatedToken(UUID id, Instant keepUntil) {
        this.id = id;
        this.keepUntil = keepUntil;
    }

}