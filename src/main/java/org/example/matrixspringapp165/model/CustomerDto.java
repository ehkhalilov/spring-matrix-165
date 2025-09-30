package org.example.matrixspringapp165.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerDto {
    private Long id;
    private String name;
    private LocalDate birthDate;
    private List<OrderDto> orders;
}
