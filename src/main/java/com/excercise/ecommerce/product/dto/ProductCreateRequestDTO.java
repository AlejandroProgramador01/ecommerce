package com.excercise.ecommerce.product.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductCreateRequestDTO {
    @NotBlank(message = "El nombre del producto es obligatorio")
    private String name;
    @NotBlank(message = "La descripción del producto es obligatoria")
    private String description;
    @NotBlank(message = "El precio del producto es obligatorio")
    private Double price;
    @NotBlank(message = "La referencia de la categoría es obligatoria")
    private Long categoryId;
    private List<ProductAttributeRequestDTO> attributes;
}
