package org.example.matrixspringapp165.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.matrixspringapp165.enums.Currency;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentDto {
    private Long id;
    private BigDecimal amount;
    private Currency currency;
//    private OrderDto order;
}
