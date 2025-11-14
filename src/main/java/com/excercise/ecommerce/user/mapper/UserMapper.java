package com.excercise.ecommerce.user.mapper;

import com.excercise.ecommerce.user.dto.*;
import com.excercise.ecommerce.user.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity mapToRegisterEntity(UserRegisterRequestDTO userRegisterRequestDTO);
    UserRegisterResponseDTO mapToRegisterResponseDTO(UserEntity userEntity);
    UserProfileResponseDTO mapToProfileResponseDTO(UserEntity userEntity);
    UserUpdateResponseDTO mapToUpdateProfileDTO(UserEntity model);
}
