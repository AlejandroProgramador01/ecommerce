package com.excercise.ecommerce.product.repository;

import com.excercise.ecommerce.product.entity.CategoryEntity;
import com.excercise.ecommerce.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    Optional<ProductEntity> findById(Long id);
    boolean existsByName(String name);
}
