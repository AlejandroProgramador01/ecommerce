package com.excercise.ecommerce.user.dto;

import com.excercise.ecommerce.user.enums.UserStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserUpdateRequestDTO {
    @NotNull(message = "El nombre de usuario es obligatorio")
    private String username;
    @NotNull(message = "La contraseña del usuario es obligatorio")
    private String password;
    @NotNull(message = "El estado del usuario es obligatorio")
    private UserStatus status;
}
