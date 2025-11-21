package com.excercise.ecommerce.product.controller;

import com.excercise.ecommerce.product.dto.ProductCreateRequestDTO;
import com.excercise.ecommerce.product.dto.ProductListItemDTO;
import com.excercise.ecommerce.product.dto.ProductResponseDTO;
import com.excercise.ecommerce.product.dto.ProductUpdateRequestDTO;
import com.excercise.ecommerce.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDTO> create(@Valid @RequestBody ProductCreateRequestDTO dto) {
        ProductResponseDTO product = productService.createProduct(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> update(@PathVariable Long id, @Valid @RequestBody ProductUpdateRequestDTO dto) {
        ProductResponseDTO product = productService.updateProduct(id, dto);
        return ResponseEntity.ok(product);
    }

    @GetMapping
    public ResponseEntity<List<ProductListItemDTO>> list() {
        return ResponseEntity.ok(productService.listProducts());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}

