package com.excercise.ecommerce.product.dto;

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
    private String name;
    private String description;
    private Double price;
    private Long categoryId;
    private List<ProductAttributeRequestDTO> attributes;
}
