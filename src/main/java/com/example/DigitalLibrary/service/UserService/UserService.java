package com.example.DigitalLibrary.service.UserService;

import com.example.DigitalLibrary.dto.ResponseDto;
import com.example.DigitalLibrary.dto.UserDto;
import com.example.DigitalLibrary.exceptions.DigitalLibraryException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface UserService {
    Optional<?> userRegistration(UserDto userDto, MultipartFile profilePic, MultipartFile coverImg) throws DigitalLibraryException, IOException;

    Optional<?> userFinalSubmit(Long userId) throws DigitalLibraryException;

    ResponseDto login(String username, String password) throws DigitalLibraryException;

    UserDto getUserDetails(Long userId) throws DigitalLibraryException;
}
