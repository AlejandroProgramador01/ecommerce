package com.excercise.ecommerce.product.repository;

import com.excercise.ecommerce.product.entity.ProductEntity;
import com.excercise.ecommerce.product.entity.QualificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QualificationRepository extends JpaRepository<QualificationEntity, Long> {
    Optional<QualificationEntity> findByProductAndUserId(ProductEntity product, Long userId);
}
