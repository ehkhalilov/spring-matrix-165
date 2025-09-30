package org.example.matrixspringapp165.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.matrixspringapp165.enums.OrderStatus;

public class OrderStatusValidator implements ConstraintValidator<ValidOrderStatus, OrderStatus> {


    @Override
    public boolean isValid(OrderStatus value, ConstraintValidatorContext context) {
        return value == OrderStatus.TO_DO;
    }
}
