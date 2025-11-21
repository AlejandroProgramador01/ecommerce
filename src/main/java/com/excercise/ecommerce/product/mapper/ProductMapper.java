package com.excercise.ecommerce.product.mapper;

import com.excercise.ecommerce.product.dto.ProductCreateRequestDTO;
import com.excercise.ecommerce.product.dto.ProductListItemDTO;
import com.excercise.ecommerce.product.dto.ProductResponseDTO;
import com.excercise.ecommerce.product.entity.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ProductAttributeMapper.class})
public interface ProductMapper {
    ProductResponseDTO mapToProductResponseDTO(ProductEntity productEntity);
    ProductListItemDTO mapToListItemDTO(ProductEntity productEntity);
    ProductEntity mapToProductEntityFromCreate(ProductCreateRequestDTO productCreateRequestDTO);
}
