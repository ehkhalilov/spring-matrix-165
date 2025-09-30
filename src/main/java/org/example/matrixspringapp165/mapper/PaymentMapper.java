package org.example.matrixspringapp165.mapper;

import org.example.matrixspringapp165.dao.entity.PaymentEntity;
import org.example.matrixspringapp165.model.PaymentDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class PaymentMapper {

    public abstract PaymentEntity mapToEntity(PaymentDto paymentDto);
}
