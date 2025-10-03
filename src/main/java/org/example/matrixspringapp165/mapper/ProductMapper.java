package org.example.matrixspringapp165.mapper;

import org.example.matrixspringapp165.dao.entity.ProductEntity;
import org.example.matrixspringapp165.model.ProductDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto mapToDto(ProductEntity productEntity);

    ProductEntity mapToEntity(ProductDto productDto);
}
