package com.excercise.ecommerce.user.dto;

import com.excercise.ecommerce.user.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserProfileResponseDTO {
    private Long id;
    private String email;
    private String username;
    private UserStatus status;
}
