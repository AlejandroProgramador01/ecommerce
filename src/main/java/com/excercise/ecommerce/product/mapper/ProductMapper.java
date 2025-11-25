package com.excercise.ecommerce.product.mapper;

import com.excercise.ecommerce.product.dto.ProductCreateRequestDTO;
import com.excercise.ecommerce.product.dto.ProductListItemDTO;
import com.excercise.ecommerce.product.dto.ProductResponseDTO;
import com.excercise.ecommerce.product.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ProductAttributeMapper.class})
public interface ProductMapper {
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.name", target = "categoryName")
    ProductResponseDTO mapToProductResponseDTO(ProductEntity productEntity);
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.name", target = "categoryName")
    ProductListItemDTO mapToListItemDTO(ProductEntity productEntity);
    ProductEntity mapToProductEntity(ProductCreateRequestDTO productCreateRequestDTO);
}
