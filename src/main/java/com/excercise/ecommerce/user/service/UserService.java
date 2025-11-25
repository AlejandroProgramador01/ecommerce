package com.excercise.ecommerce.user.service;

import com.excercise.ecommerce.user.dto.*;

public interface UserService {
    UserRegisterResponseDTO signUp(UserRegisterRequestDTO user);
    UserLoginResponseDTO login(UserLoginRequestDTO user);
    UserProfileResponseDTO getProfile(String email);
    void deleteProfile(String email);
    UserUpdateResponseDTO updateProfile(String email, UserUpdateRequestDTO user);
}
