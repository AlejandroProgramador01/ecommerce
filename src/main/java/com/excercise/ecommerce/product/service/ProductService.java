package com.excercise.ecommerce.product.service;

import com.excercise.ecommerce.product.dto.*;

import java.util.List;

public interface ProductService {
    ProductResponseDTO createProduct(ProductCreateRequestDTO productCreateRequestDTO);
    ProductResponseDTO updateProduct(Long id, ProductUpdateRequestDTO productUpdateRequestDTO);
    List<ProductListItemDTO> listProducts();
    void deleteProduct(Long id);
}
