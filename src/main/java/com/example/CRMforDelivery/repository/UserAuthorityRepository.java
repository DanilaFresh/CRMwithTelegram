package com.example.CRMforDelivery.repository;

import com.example.CRMforDelivery.entity.UserAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAuthorityRepository extends JpaRepository<UserAuthority,Long> {
    List<UserAuthority> findByUserId(Long userId);
}
