package com.excercise.ecommerce.product.mapper;

import com.excercise.ecommerce.product.dto.QualificationCreateRequestDTO;
import com.excercise.ecommerce.product.dto.QualificationResponseDTO;
import com.excercise.ecommerce.product.entity.QualificationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface QualificationMapper {
    QualificationEntity mapToEntity(QualificationCreateRequestDTO productCreateRequestDTO);
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.username", target = "userName")
    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "productName")
    QualificationResponseDTO mapToResponseDTO(QualificationEntity qualificationEntity);
}
