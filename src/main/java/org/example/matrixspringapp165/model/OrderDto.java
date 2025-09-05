package org.example.matrixspringapp165.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.matrixspringapp165.enums.OrderStatus;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderDto {
    private Long id;
    @NotBlank
    private String number;
    @PositiveOrZero
    @Min(5)
    @Max(10)
    private Integer quantity;
    private OrderStatus orderStatus;
    @FutureOrPresent
    private LocalDate createAt;
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@gmail.com", message = "INVALID_GMAIL_FORMAT")
    private String customerEmail;
}
