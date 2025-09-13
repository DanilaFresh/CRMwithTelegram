package com.example.CRMforDelivery.repository;

import com.example.CRMforDelivery.entity.Courier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourierRepository extends JpaRepository<Courier,Long> {

    Optional<Courier> findByTgUserName(String tgUserName);
}
