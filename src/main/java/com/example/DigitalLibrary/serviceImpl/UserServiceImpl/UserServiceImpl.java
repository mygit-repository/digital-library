package com.example.DigitalLibrary.serviceImpl.UserServiceImpl;

import com.example.DigitalLibrary.constants.ErrorCode;
import com.example.DigitalLibrary.constants.Role;
import com.example.DigitalLibrary.constants.UserStatus;
import com.example.DigitalLibrary.dto.ResponseDto;
import com.example.DigitalLibrary.dto.UserDto;
import com.example.DigitalLibrary.entity.User;
import com.example.DigitalLibrary.exceptions.DigitalLibraryException;
import com.example.DigitalLibrary.repository.UserRepository;
import com.example.DigitalLibrary.service.UserService.UserService;
import com.example.DigitalLibrary.utils.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW, rollbackOn = Exception.class)
    public Optional<?> userRegistration(UserDto userDto, MultipartFile profileImage, MultipartFile coverImage) throws DigitalLibraryException, IOException {
        User user;
        if (userDto.getUserId() == null) {
            user = new User();
            user.setStatus(UserStatus.ACTIVE);
            userRepository.save(user);
        } else {
            user = userRepository.findByIdAndIsDeleted(userDto.getUserId(), false)
                    .orElseThrow(() -> new DigitalLibraryException(ErrorCode.ENTITY_NOT_FOUND, "User not found with the provided userId: " + userDto.getUserId()));
        }

        user.setFullName(userDto.getFullName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        user.setStatus(UserStatus.DRAFT);
        /**
         * Image uploaded here...
         */
        String profileImg = profileImage.getName();
        user.setProfileImagePath(profileImg);

        return Optional.ofNullable(ResponseDto.builder().id(user.getId()).status("success").message("Saved Successfully").build());
    }

    @Override
    public Optional<?> userFinalSubmit(Long userId) throws DigitalLibraryException {

        User user = userRepository.findByIdAndIsDeleted(userId, false)
                .orElseThrow(() -> new DigitalLibraryException(
                        ErrorCode.ENTITY_NOT_FOUND,
                        "User not found with provided userId: " + userId
                ));

        if (UserStatus.ACTIVE.equals(user.getStatus())) {
            throw new DigitalLibraryException(ErrorCode.REQUEST_ERROR, "User already submitted successfully");
        }

        if (UserStatus.BLOCKED.equals(user.getStatus())) {
            throw new DigitalLibraryException(ErrorCode.REQUEST_ERROR, "User is blocked");
        }

        //Generate RegdNo
        String regdNo = generateRegdNo(user.getRole());
        user.setRegdNo(regdNo);

        user.setStatus(UserStatus.ACTIVE);

        userRepository.save(user);

        return Optional.ofNullable(
                ResponseDto.builder()
                        .id(user.getId())
                        .status("success")
                        .message("Saved Successfully")
                        .userRegdNo(user.getRegdNo())
                        .build()
        );
    }

    @Override
    public ResponseDto login(String username, String password) {

        User user = userRepository.findByEmailOrRegdNo(username, username)
                .orElseThrow(() -> new DigitalLibraryException(
                        ErrorCode.ENTITY_NOT_FOUND, "Invalid credentials"));

        if (!user.getPassword().equals(password)) {
            throw new DigitalLibraryException(ErrorCode.REQUEST_ERROR, "Invalid password");
        }

        String token = jwtUtil.generateToken(username);

        return ResponseDto.builder()
                .status("success")
                .message("Login successful")
                .token(token)
                .build();
    }

    @Override
    public UserDto getUserDetails(Long userId) throws DigitalLibraryException {
        User user = userRepository.findByIdAndIsDeleted(userId, false)
                .orElseThrow(() -> new DigitalLibraryException(ErrorCode.ENTITY_NOT_FOUND, "User not found with provided userId: " + userId));

        UserDto userDto = new UserDto();
        userDto.setUserId(userId);
        userDto.setFullName(user.getFullName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setRole(user.getRole());
        userDto.setRegdNo(user.getRegdNo());
        userDto.setStatus(user.getStatus());
        userDto.setProfileImagePath(user.getProfileImagePath());
        userDto.setCoverImagePath(user.getCoverImagePath());
        return userDto;
    }

    private String generateRegdNo(Role role) {

        String prefix = role.getPrefix();
        String code = role.getCode();

        List<String> result = userRepository.findTopRegdNoByRole(role, PageRequest.of(0, 1));

        int nextSerial = 1;

        if (!result.isEmpty()) {
            String lastRegdNo = result.get(0); // e.g. STA-03-09

            String[] parts = lastRegdNo.split("-");
            int lastSerial = Integer.parseInt(parts[2]);

            nextSerial = lastSerial + 1;
        }

        String serialFormatted = nextSerial < 100
                ? String.format("%02d", nextSerial)
                : String.valueOf(nextSerial);

        return prefix + "-" + code + "-" + serialFormatted;
    }

}
