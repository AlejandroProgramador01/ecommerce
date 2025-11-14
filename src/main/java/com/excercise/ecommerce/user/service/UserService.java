package com.excercise.ecommerce.user.service;

import com.excercise.ecommerce.user.dto.*;
import com.excercise.ecommerce.user.entity.UserEntity;

public interface UserService {
    UserRegisterResponseDTO signUp(UserRegisterRequestDTO user);
    UserLoginResponseDTO login(UserLoginRequestDTO user);
    UserProfileResponseDTO getProfileByEmail(String email);
    void deleteProfileByEmail(String email);
    UserUpdateResponseDTO updateProfileByEmail(String email, UserUpdateRequestDTO user);
}
