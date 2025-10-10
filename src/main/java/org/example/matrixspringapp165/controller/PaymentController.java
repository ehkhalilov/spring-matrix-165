package org.example.matrixspringapp165.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.matrixspringapp165.model.PaymentDto;
import org.example.matrixspringapp165.service.PaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
@Tag(name = "Payments", description = "Operations related to customer payments")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    @Operation(
            summary = "Get all payments",
            description = "Returns list of all customer payments from DB"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of payments successfully returned",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PaymentDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public void createPayment(@RequestBody PaymentDto paymentDto) {
        paymentService.createPayment(paymentDto);
    }
}
