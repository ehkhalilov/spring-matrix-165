package org.example.matrixspringapp165.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.matrixspringapp165.enums.Currency;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Schema(description = "Request object for creating a new payment")
public class PaymentDto {
    @Schema(description = "Id of requested payment")
    private Long id;
    @Schema(description = "Name of created payment")
    private BigDecimal amount;
    @Schema(description = "Currency of created payment")
    private Currency currency;
//    private OrderDto order;
}
