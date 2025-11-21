package com.excercise.ecommerce.product.mapper;

import com.excercise.ecommerce.product.dto.CategoryCreateRequestDTO;
import com.excercise.ecommerce.product.dto.CategoryListItemDTO;
import com.excercise.ecommerce.product.dto.CategoryResponseDTO;
import com.excercise.ecommerce.product.entity.CategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryListItemDTO mapToCategoryListItemDTO(CategoryEntity categoryEntity);
    CategoryResponseDTO mapToCategoryResponseDTO(CategoryEntity categoryEntity);
    CategoryEntity mapToCategoryEntityFromRequest(CategoryCreateRequestDTO categoryCreateRequestDTO);
}
