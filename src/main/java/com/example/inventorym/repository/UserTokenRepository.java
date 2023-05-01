package com.example.inventorym.repository;

import com.example.inventorym.entity.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserTokenRepository extends JpaRepository<UserToken, UUID> {

    UserToken findByToken(String token);
    UserToken findByUserId(UUID userId);
}