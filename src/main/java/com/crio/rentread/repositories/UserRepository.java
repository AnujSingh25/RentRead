package com.crio.rentread.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crio.rentread.models.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByEmail(String email);
}
