package com.example.product.mapping;

import com.example.product.dto.ProductDTO;
import com.example.product.model.ProductModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDTO toDTO(ProductModel productModel);

    ProductModel toEntity(ProductDTO dto);
}
