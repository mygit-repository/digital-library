package com.example.DigitalLibrary.entity;

import com.example.DigitalLibrary.constants.Permission;
import com.example.DigitalLibrary.constants.Role;
import com.example.DigitalLibrary.constants.UserStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "users")
public class User extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "full_name", nullable = false)
    String fullName;

    @Column(name = "email", nullable = false, unique = true)
    String email;

    @Column(name = "password", nullable = false)
    String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    Role role; // SUPER_ADMIN, OFFICIAL, STUDENT

    @Column(name = "regd_no", length = 10)
    String regdNo;

//    @ElementCollection(fetch = FetchType.EAGER)
//    @Enumerated(EnumType.STRING)
//    @CollectionTable(name = "user_permissions", joinColumns = @JoinColumn(name = "user_id"))
//    @Column(name = "permission")
//    Set<Permission> permissions = new HashSet<>(); // Permissions ONLY for OFFICIAL (ignored for STUDENT)

    @Column(name = "profile_image_path")
    String profileImagePath;

    @Column(name = "cover_image_path")
    String coverImagePath;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    UserStatus status;

    @Column(name = "is_deleted")
    Boolean isDeleted = false; //True means deleted, false means not deleted
}
