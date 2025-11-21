package com.excercise.ecommerce.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductResponseDTO {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Long categoryId;
    private String categoryName;
    private List<ProductAttributeResponseDTO> attributes;
    private Double averageRating;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
