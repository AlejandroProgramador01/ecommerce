package com.excercise.ecommerce.user.service;

import com.excercise.ecommerce.config.JwtUtil;
import com.excercise.ecommerce.user.dto.*;
import com.excercise.ecommerce.user.entity.UserEntity;
import com.excercise.ecommerce.user.exception.DuplicateEmailException;
import com.excercise.ecommerce.user.exception.InvalidCredentialsException;
import com.excercise.ecommerce.user.exception.UserNotFoundException;
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
    public UserRegisterResponseDTO signUp(UserRegisterRequestDTO userRegisterRequestDTO) {
        if (userRepository.existsByEmail(userRegisterRequestDTO.getEmail())) {
            throw new DuplicateEmailException(userRegisterRequestDTO.getEmail());
        }
        UserEntity userEntity = userMapper.mapToRegisterEntity(userRegisterRequestDTO);
        userEntity.setPassword(passwordEncoder.encode(userRegisterRequestDTO.getPassword()));
        UserEntity userCreated = userRepository.save(userEntity);
        UserRegisterResponseDTO user = userMapper.mapToRegisterResponseDTO(userCreated);
        return user;
    }

    @Override
    public UserLoginResponseDTO login(UserLoginRequestDTO userLoginRequestDTO) {
        UserEntity userEntity = userRepository.findByEmail(userLoginRequestDTO.getEmail())
                .orElseThrow(() -> new UserNotFoundException(userLoginRequestDTO.getEmail()));
        if (!passwordEncoder.matches(userLoginRequestDTO.getPassword(), userEntity.getPassword())) {
            throw new InvalidCredentialsException("Contraseña incorrecta");
        }
        String token = jwtUtil.generateToken(userEntity.getEmail());
        LocalDateTime expirationDate = jwtUtil.getExpirationDate();
        UserLoginResponseDTO response = new UserLoginResponseDTO();
        response.setToken(token);
        response.setTokenExpiration(expirationDate);
        return response;
    }

    @Override
    public UserProfileResponseDTO getProfileByEmail(String email) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado"));
        return userMapper.mapToProfileResponseDTO(user);
    }

    @Override
    @Transactional
    public void deleteProfileByEmail(String email) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado"));
        userRepository.delete(user);
    }

    @Override
    @Transactional
    public UserUpdateResponseDTO updateProfileByEmail(String email, UserUpdateRequestDTO dto) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado"));
        if (dto.getUsername() != null) {
            user.setUsername(dto.getUsername());
        }
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        UserEntity updated = userRepository.save(user);
        return userMapper.mapToUpdateProfileDTO(updated);
    }
}

