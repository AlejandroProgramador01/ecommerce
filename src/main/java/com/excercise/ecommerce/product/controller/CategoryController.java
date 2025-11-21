package com.excercise.ecommerce.product.controller;

import com.excercise.ecommerce.product.dto.CategoryCreateRequestDTO;
import com.excercise.ecommerce.product.dto.CategoryListItemDTO;
import com.excercise.ecommerce.product.dto.CategoryResponseDTO;
import com.excercise.ecommerce.product.dto.CategoryUpdateRequestDTO;
import com.excercise.ecommerce.product.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> create(@Valid @RequestBody CategoryCreateRequestDTO dto) {
        CategoryResponseDTO category = categoryService.createCategory(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> update(@PathVariable Long id, @Valid @RequestBody CategoryUpdateRequestDTO dto) {
        CategoryResponseDTO category = categoryService.updateCategory(id, dto);
        return ResponseEntity.ok(category);
    }

    @GetMapping
    public ResponseEntity<List<CategoryListItemDTO>> list() {
        return ResponseEntity.ok(categoryService.listCategories());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}


