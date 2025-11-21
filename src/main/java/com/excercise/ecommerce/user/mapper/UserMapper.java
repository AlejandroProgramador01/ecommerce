package com.excercise.ecommerce.user.mapper;

import com.excercise.ecommerce.user.dto.*;
import com.excercise.ecommerce.user.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity mapToRegisterEntity(UserRegisterRequestDTO dto);
    UserRegisterResponseDTO mapToRegisterResponseDTO(UserEntity entity);
    UserProfileResponseDTO mapToProfileResponseDTO(UserEntity entity);
    UserUpdateResponseDTO mapToUpdateProfileDTO(UserEntity entity);
}
