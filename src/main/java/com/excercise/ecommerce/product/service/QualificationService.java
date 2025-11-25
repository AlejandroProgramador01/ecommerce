package com.excercise.ecommerce.product.service;

import com.excercise.ecommerce.product.dto.QualificationCreateRequestDTO;
import com.excercise.ecommerce.product.dto.QualificationResponseDTO;
import com.excercise.ecommerce.product.dto.QualificationUpdateRequestDTO;

import java.util.List;

public interface QualificationService {
    QualificationResponseDTO createQualification(QualificationCreateRequestDTO dto, String userEmail);
    QualificationResponseDTO updateQualification(Long id, QualificationUpdateRequestDTO dto, String userEmail);
    void deleteQualification(Long id, String userEmail);
    List<QualificationResponseDTO> getProductQualifications(Long productId);
}
