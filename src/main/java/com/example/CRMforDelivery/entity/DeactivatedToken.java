package com.example.CRMforDelivery.entity;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "deactivated_tokens")
public class DeactivatedToken {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "c_keep_until", nullable = false)
    private Instant keepUntil;

    public DeactivatedToken() {}

    public DeactivatedToken(UUID id, Instant keepUntil) {
        this.id = id;
        this.keepUntil = keepUntil;
    }

    public Instant getKeepUntil() {
        return keepUntil;
    }

    public void setKeepUntil(Instant keepUntil) {
        this.keepUntil = keepUntil;
    }
}