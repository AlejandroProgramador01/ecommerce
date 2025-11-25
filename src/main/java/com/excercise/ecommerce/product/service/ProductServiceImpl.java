package com.excercise.ecommerce.product.service;

import com.excercise.ecommerce.exception.DuplicateException;
import com.excercise.ecommerce.exception.NotFoundException;
import com.excercise.ecommerce.product.dto.ProductCreateRequestDTO;
import com.excercise.ecommerce.product.dto.ProductListItemDTO;
import com.excercise.ecommerce.product.dto.ProductResponseDTO;
import com.excercise.ecommerce.product.dto.ProductUpdateRequestDTO;
import com.excercise.ecommerce.product.entity.CategoryEntity;
import com.excercise.ecommerce.product.entity.ProductAttributeEntity;
import com.excercise.ecommerce.product.entity.ProductEntity;
import com.excercise.ecommerce.product.enums.ProductStatus;
import com.excercise.ecommerce.product.mapper.ProductAttributeMapper;
import com.excercise.ecommerce.product.mapper.ProductMapper;
import com.excercise.ecommerce.product.repository.CategoryRepository;
import com.excercise.ecommerce.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;
    private final ProductAttributeMapper attributeMapper;

    @Override
    @Transactional
    public ProductResponseDTO createProduct(ProductCreateRequestDTO dto) {
        if (productRepository.existsByName(dto.getName())) {
            throw new DuplicateException("El producto ya existe");
        }
        CategoryEntity category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new NotFoundException("La categoría no existe"));
        ProductEntity product = productMapper.mapToProductEntity(dto);
        product.setCategory(category);
        product.setStatus(ProductStatus.UNAVAILABLE);
        List<ProductAttributeEntity> attributes = dto.getAttributes()
                .stream()
                .map(attributeMapper::mapToProductAttributeEntity)
                .peek(attr -> attr.setProduct(product))
                .toList();
        product.getAttributes().addAll(attributes);
        ProductEntity saved = productRepository.save(product);
        return productMapper.mapToProductResponseDTO(saved);
    }

    @Override
    @Transactional
    public ProductResponseDTO updateProduct(Long id, ProductUpdateRequestDTO dto) {
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("El producto no existe"));
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStatus(dto.getStatus());
        CategoryEntity category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new NotFoundException("La categoría no existe"));
        product.setCategory(category);
        product.getAttributes().clear();
        List<ProductAttributeEntity> updatedAttributes = dto.getAttributes()
                .stream()
                .map(attributeMapper::mapToProductAttributeEntity)
                .peek(attr -> attr.setProduct(product))
                .toList();
        product.getAttributes().addAll(updatedAttributes);
        productRepository.save(product);
        return productMapper.mapToProductResponseDTO(product);
    }

    @Override
    public List<ProductListItemDTO> listProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::mapToListItemDTO)
                .toList();
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("El producto no existe"));
        productRepository.delete(product);
    }
}
