package org.example.matrixspringapp165.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.matrixspringapp165.enums.OrderStatus;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderDto {
    private Long id;
    private String number;
    private Integer quantity;
    private OrderStatus orderStatus;
}
