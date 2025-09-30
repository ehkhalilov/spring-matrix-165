package org.example.matrixspringapp165.mapper;

import org.example.matrixspringapp165.dao.entity.CustomerEntity;
import org.example.matrixspringapp165.model.CustomerDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerEntity dtoToEntity(CustomerDto customerDto);

    CustomerDto entityToDto(CustomerEntity customerEntity);
}
