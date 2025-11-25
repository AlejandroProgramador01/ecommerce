package com.excercise.ecommerce.product.dto;

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
public class CategoryCreateRequestDTO {
    @NotBlank(message = "El nombre de la categoría es obligatorio")
    private String name;
    @NotBlank(message = "La descripción de la categoría es obligatoria")
    @Size(min = 30, max = 200, message = "La descripción debe tener entre 5 y 200 caracteres")
    private String description;
}
