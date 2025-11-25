package com.excercise.ecommerce.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserLoginRequestDTO {
    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Debe ingresar un correo válido")
    private String email;
    @NotBlank(message = "La contraseña es obligatoria")
    private String password;
}

