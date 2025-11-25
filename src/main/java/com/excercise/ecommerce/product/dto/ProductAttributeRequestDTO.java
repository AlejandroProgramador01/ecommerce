package com.excercise.ecommerce.product.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductAttributeRequestDTO {
    @NotBlank(message = "El nombre del atributo es obligatorio")
    private String name;
    @NotBlank(message = "El valor del atributo es obligatorio")
    private String attributeValue;
}
