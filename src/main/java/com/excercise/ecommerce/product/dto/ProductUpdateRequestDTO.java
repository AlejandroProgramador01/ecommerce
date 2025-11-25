package com.excercise.ecommerce.product.dto;

import com.excercise.ecommerce.product.enums.ProductStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdateRequestDTO {
    @NotBlank(message = "El nombre del producto es obligatorio")
    private String name;
    @NotBlank(message = "La descripción del producto es obligatoria")
    private String description;
    @NotBlank(message = "El precio del producto es obligatorio")
    private Double price;
    @NotBlank(message = "La referencia de la categoría es obligatoria")
    private Long categoryId;
    @NotBlank(message = "El estado del producto es obligatorio")
    private ProductStatus status;
    private List<ProductAttributeRequestDTO> attributes;
}

