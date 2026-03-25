package com.example.DigitalLibrary.controller.User;

import com.example.DigitalLibrary.dto.UserDto;
import com.example.DigitalLibrary.service.UserService.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/save-user")
    public ResponseEntity<?> saveUser(@RequestPart @Valid UserDto userDto,
                                      @RequestParam(name = "profileImage", required = false)MultipartFile profileImage,
                                      @RequestParam(name = "coverImage", required = false) MultipartFile coverImage) throws Exception {
        return ResponseEntity.ok(userService.saveUser(userDto, profileImage, coverImage));
    }

    @GetMapping("/get-user")
    public ResponseEntity<?> getUserDetails(@RequestParam("userId") Long userId) throws Exception {
        return ResponseEntity.ok(userService.getUserDetails(userId));
    }
}
