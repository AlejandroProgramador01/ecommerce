package com.excercise.ecommerce.product.dto;

import com.excercise.ecommerce.product.enums.ProductStatus;
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
    private String name;
    private String description;
    private Double price;
    private Long categoryId;
    private ProductStatus status;
    private List<ProductAttributeRequestDTO> attributes;
}

