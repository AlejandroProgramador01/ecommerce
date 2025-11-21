package com.excercise.ecommerce.user.controller;

import com.excercise.ecommerce.user.dto.*;
import com.excercise.ecommerce.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponseDTO> register(@Valid @RequestBody UserRegisterRequestDTO dto) {
        UserRegisterResponseDTO user = userService.signUp(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDTO> login(@Valid @RequestBody UserLoginRequestDTO dto) {
        UserLoginResponseDTO user = userService.login(dto);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/me")
    public ResponseEntity<UserProfileResponseDTO> getMyProfile(Authentication authentication) {
        String email = authentication.getName();
        UserProfileResponseDTO user = userService.getProfileByEmail(email);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/me")
    public ResponseEntity<Void> deleteMyAccount(Authentication authentication) {
        String email = authentication.getName();
        userService.deleteProfileByEmail(email);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/me")
    public ResponseEntity<UserUpdateResponseDTO> updateMyProfile(
            Authentication authentication, @Valid @RequestBody UserUpdateRequestDTO dto
    ) {
        String email = authentication.getName();
        UserUpdateResponseDTO user = userService.updateProfileByEmail(email, dto);
        return ResponseEntity.ok(user);
    }
}