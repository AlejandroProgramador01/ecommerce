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
public class ProductListItemDTO     {
    private Long id;
    private String name;
    private Double price;
    private Long categoryId;
    private String categoryName;
    private Double averageRating;
    private String status;
}
