package org.example.matrixspringapp165.mapper;

import org.example.matrixspringapp165.dao.entity.OrderEntity;
import org.example.matrixspringapp165.model.OrderDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public abstract class OrderMapper {

    @Mapping(target = "number", source = "orderNumber", qualifiedByName = "getNumber")
    public abstract OrderDto mapToDto(OrderEntity entity);

    @Mapping(target = "orderNumber", source = "number")
    @Mapping(target = "quantity", source = "quantity", defaultValue = "5")
    public abstract OrderEntity mapToEntity(OrderDto orderDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "orderNumber", source = "number")
    public abstract void mapToEntityModify(@MappingTarget OrderEntity orderEntity,
                                           OrderDto orderDto);

    @Named(value = "getNumber")
    protected String getNumber(String orderNumber) {
        return orderNumber.toUpperCase();
    }
}
