package com.example.DigitalLibrary.serviceImpl.UserServiceImpl;

import com.example.DigitalLibrary.constants.ErrorCode;
import com.example.DigitalLibrary.constants.UserStatus;
import com.example.DigitalLibrary.dto.ResponseDto;
import com.example.DigitalLibrary.dto.UserDto;
import com.example.DigitalLibrary.entity.User;
import com.example.DigitalLibrary.exceptions.DigitalLibraryException;
import com.example.DigitalLibrary.repository.UserRepository;
import com.example.DigitalLibrary.service.UserService.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW, rollbackOn = Exception.class)
    public Optional<?> saveUser(UserDto userDto, MultipartFile profileImage, MultipartFile coverImage) throws DigitalLibraryException, IOException {
        User user;
        if (userDto.getUserId() == null) {
            user = new User();
            user.setStatus(UserStatus.ACTIVE);
            userRepository.save(user);
        } else {
            user = userRepository.findByIdAndIsDeleted(userDto.getUserId(), false)
                    .orElseThrow(()-> new DigitalLibraryException(ErrorCode.ENTITY_NOT_FOUND, "User not found with the provided userId: "+userDto.getUserId()));
        }

        user.setFullName(userDto.getFullName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        /**
         * Image uploaded here...
         */
        String profileImg = profileImage.getName();
        user.setProfileImagePath(profileImg);

        return Optional.ofNullable(ResponseDto.builder().id(user.getId()).status("success").message("Saved Successfully").build());
    }

    @Override
    public UserDto getUserDetails(Long userId) throws DigitalLibraryException {
        User user = userRepository.findByIdAndIsDeleted(userId, false)
                .orElseThrow(()-> new DigitalLibraryException(ErrorCode.ENTITY_NOT_FOUND, "User not found with provided userId: "+ userId));

        UserDto userDto = new UserDto();
        userDto.setUserId(userId);
        userDto.setFullName(user.getFullName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setRole(user.getRole());
        userDto.setStatus(user.getStatus());
        userDto.setProfileImagePath(user.getProfileImagePath());
        userDto.setCoverImagePath(user.getCoverImagePath());
        return userDto;
    }
}
