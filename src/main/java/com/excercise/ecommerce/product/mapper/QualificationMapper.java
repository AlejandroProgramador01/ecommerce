package com.excercise.ecommerce.product.mapper;

import com.excercise.ecommerce.product.dto.ProductCreateRequestDTO;
import com.excercise.ecommerce.product.dto.ProductUpdateRequestDTO;
import com.excercise.ecommerce.product.dto.QualificationResponseDTO;
import com.excercise.ecommerce.product.entity.QualificationEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QualificationMapper {
    QualificationEntity mapToEntityFromUpdate(ProductUpdateRequestDTO productUpdateRequestDTO);
    QualificationEntity mapToEntityFromCreate(ProductCreateRequestDTO productCreateRequestDTO);
    QualificationResponseDTO mapToResponseDTO(QualificationEntity qualificationEntity);
}
