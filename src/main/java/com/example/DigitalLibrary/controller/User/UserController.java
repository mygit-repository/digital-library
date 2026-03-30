package com.example.DigitalLibrary.controller.User;

import com.example.DigitalLibrary.dto.ResponseDto;
import com.example.DigitalLibrary.dto.UserDto;
import com.example.DigitalLibrary.service.UserService.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/save-user")
    public ResponseEntity<?> userRegistration(@RequestPart @Valid UserDto userDto,
                                              @RequestParam(name = "profileImage", required = false) MultipartFile profileImage,
                                              @RequestParam(name = "coverImage", required = false) MultipartFile coverImage) throws Exception {
        return ResponseEntity.ok(userService.userRegistration(userDto, profileImage, coverImage));
    }

    @PostMapping("/final-submit")
    public ResponseEntity<?> userFinalSubmit(@RequestParam(name = "userId") Long userId) throws Exception {
        return ResponseEntity.ok(userService.userFinalSubmit(userId));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam(name = "username") String username,
                                   @RequestParam(name = "password") String password) throws Exception {
        return ResponseEntity.ok(userService.login(username, password));
    }

    @GetMapping("/get-user")
    public ResponseEntity<?> getUserDetails(@RequestParam("userId") Long userId) throws Exception {
        return ResponseEntity.ok(userService.getUserDetails(userId));
    }
}
