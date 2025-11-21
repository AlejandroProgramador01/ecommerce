package com.excercise.ecommerce.user.service;

import com.excercise.ecommerce.config.JwtUtil;
import com.excercise.ecommerce.user.dto.*;
import com.excercise.ecommerce.user.entity.UserEntity;
import com.excercise.ecommerce.user.enums.UserStatus;
import com.excercise.ecommerce.exception.DuplicateException;
import com.excercise.ecommerce.exception.InvalidCredentialsException;
import com.excercise.ecommerce.exception.NotFoundException;
import com.excercise.ecommerce.user.mapper.UserMapper;
import com.excercise.ecommerce.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    @Transactional
    public UserRegisterResponseDTO signUp(UserRegisterRequestDTO dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new DuplicateException("El usuario ya existe");
        }
        UserEntity userEntity = userMapper.mapToRegisterEntity(dto);
        userEntity.setPassword(passwordEncoder.encode(dto.getPassword()));
        userEntity.setStatus(UserStatus.ACTIVE);
        UserEntity userCreated = userRepository.save(userEntity);
        return userMapper.mapToRegisterResponseDTO(userCreated);
    }

    @Override
    public UserLoginResponseDTO login(UserLoginRequestDTO dto) {
        UserEntity userEntity = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new NotFoundException("El usuario no existe"));
        if (!passwordEncoder.matches(dto.getPassword(), userEntity.getPassword())) {
            throw new InvalidCredentialsException("Contraseña incorrecta");
        }
        String token = jwtUtil.generateToken(userEntity.getEmail());
        LocalDateTime expirationDate = jwtUtil.getExpirationDate();
        UserLoginResponseDTO user = new UserLoginResponseDTO();
        user.setToken(token);
        user.setTokenExpiration(expirationDate);
        return user;
    }

    @Override
    public UserProfileResponseDTO getProfileByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("El usuario no existe"));
        return userMapper.mapToProfileResponseDTO(userEntity);
    }

    @Override
    @Transactional
    public void deleteProfileByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("El usuario no existe"));
        userRepository.delete(userEntity);
    }

    @Override
    @Transactional
    public UserUpdateResponseDTO updateProfileByEmail(String email, UserUpdateRequestDTO dto) {
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("El usuario no existe"));
        if (
                dto.getUsername() != null && !dto.getUsername().isBlank()
                        && dto.getPassword() != null && !dto.getPassword().isBlank()
        ) {
            userEntity.setUsername(dto.getUsername());
            userEntity.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        UserEntity user = userRepository.save(userEntity);
        return userMapper.mapToUpdateProfileDTO(user);
    }
}

