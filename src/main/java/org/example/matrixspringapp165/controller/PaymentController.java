package org.example.matrixspringapp165.controller;

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
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    public void createPayment(@RequestBody PaymentDto paymentDto) {
        paymentService.createPayment(paymentDto);
    }
}
