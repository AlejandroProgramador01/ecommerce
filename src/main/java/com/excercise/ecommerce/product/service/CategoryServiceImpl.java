package com.excercise.ecommerce.product.service;

import com.excercise.ecommerce.exception.DuplicateException;
import com.excercise.ecommerce.exception.NotFoundException;
import com.excercise.ecommerce.product.dto.CategoryCreateRequestDTO;
import com.excercise.ecommerce.product.dto.CategoryListItemDTO;
import com.excercise.ecommerce.product.dto.CategoryResponseDTO;
import com.excercise.ecommerce.product.dto.CategoryUpdateRequestDTO;
import com.excercise.ecommerce.product.entity.CategoryEntity;
import com.excercise.ecommerce.product.mapper.CategoryMapper;
import com.excercise.ecommerce.product.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryResponseDTO createCategory(CategoryCreateRequestDTO dto) {
        if (categoryRepository.existsByName(dto.getName())) {
            throw new DuplicateException("La categoría ya existe");
        }
        CategoryEntity category = categoryMapper.mapToCategoryEntityFromRequest(dto);
        categoryRepository.save(category);
        return categoryMapper.mapToCategoryResponseDTO(category);
    }

    @Override
    public CategoryResponseDTO updateCategory(Long id, CategoryUpdateRequestDTO dto) {
        CategoryEntity category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("La categoría no existe"));
        if(dto.getName() != null) {
            category.setName(dto.getName());
        }
        if(dto.getDescription() != null) {
            category.setDescription(dto.getDescription());
        }
        category.setId(id);
        CategoryEntity updated = categoryRepository.save(category);
        return categoryMapper.mapToCategoryResponseDTO(updated);
    }

    @Override
    public List<CategoryListItemDTO> listCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::mapToCategoryListItemDTO)
                .toList();
    }

    @Override
    @Transactional
    public void deleteCategory(Long id) {
        CategoryEntity category = categoryRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("La categoría no existe"));
        categoryRepository.delete(category);
    }
}
