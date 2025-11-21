package com.excercise.ecommerce.product.mapper;

import com.excercise.ecommerce.product.dto.*;
import com.excercise.ecommerce.product.entity.ProductAttributeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductAttributeMapper {
        ProductAttributeResponseDTO mapToProductAttributeResponseDTO(ProductAttributeEntity productAttributeEntity);
        ProductAttributeEntity mapToProductAttributeEntity(ProductAttributeRequestDTO productCreateRequestDTO);
}
