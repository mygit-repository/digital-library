package com.example.DigitalLibrary.dto;

import com.example.DigitalLibrary.constants.Role;
import com.example.DigitalLibrary.constants.UserStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Valid
public class UserDto {

    Long userId;

    @NotBlank(message = "fullName can't be null or empty")
    @Size(max = 50, message = "Name can't be more than 50 characters")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Only alphabets and spaces are allowed")
    String fullName;

    @NotBlank(message = "email can't be null")
    @Email(message = "Invalid email")
    String email;

    @NotBlank(message = "Password can't be null or empty")
    @Size(min = 12, max = 20, message = "Password must be 12–20 characters")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{12,20}$", message = "Password must contain letters, numbers, and at least one special character")
    private String password;

    @NotNull(message = "role can't be null")
    Role role;  // SUPER_ADMIN, OFFICIAL, STUDENT

    @NotBlank(message = "regdNo can't be null or empty")
    String regdNo;

    @NotNull(message = "status can't be null")
    UserStatus status; // ACTIVE, BLOCKED (optional)

    String profileImagePath;

    String coverImagePath;
}
