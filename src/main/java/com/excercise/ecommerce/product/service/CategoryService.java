package com.excercise.ecommerce.product.service;

import com.excercise.ecommerce.product.dto.CategoryCreateRequestDTO;
import com.excercise.ecommerce.product.dto.CategoryListItemDTO;
import com.excercise.ecommerce.product.dto.CategoryResponseDTO;
import com.excercise.ecommerce.product.dto.CategoryUpdateRequestDTO;
import java.util.List;

public interface CategoryService {
    CategoryResponseDTO createCategory(CategoryCreateRequestDTO categoryCreateRequestDTO);
    CategoryResponseDTO updateCategory(Long id, CategoryUpdateRequestDTO categoryUpdateRequestDTO);
    List<CategoryListItemDTO> listCategories();
    void deleteCategory(Long id);
}
