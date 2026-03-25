package com.example.DigitalLibrary.service.UserService;

import com.example.DigitalLibrary.dto.UserDto;
import com.example.DigitalLibrary.exceptions.DigitalLibraryException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface UserService {
    Optional<?> saveUser(UserDto userDto, MultipartFile profilePic, MultipartFile coverImg) throws DigitalLibraryException, IOException;

    UserDto getUserDetails(Long userId) throws DigitalLibraryException;
}
