package com.example.DigitalLibrary.repository;

import com.example.DigitalLibrary.constants.Role;
import com.example.DigitalLibrary.entity.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByIdAndIsDeleted(Long userId, boolean isDeleted);

    @Query("""
                SELECT u.regdNo 
                FROM User u 
                WHERE u.role = :role AND u.isDeleted = false 
                ORDER BY u.regdNo DESC
            """)
    List<String> findTopRegdNoByRole(@Param("role") Role role, Pageable pageable);

    Optional<User> findByEmailOrRegdNo(String email, String regdNo);
}
