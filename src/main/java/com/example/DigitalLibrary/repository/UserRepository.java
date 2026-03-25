package com.example.DigitalLibrary.repository;

import com.example.DigitalLibrary.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByIdAndIsDeleted(Long userId, boolean isDeleted);
}
