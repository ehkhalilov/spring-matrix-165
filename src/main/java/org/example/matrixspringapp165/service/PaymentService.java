package org.example.matrixspringapp165.service;

import lombok.RequiredArgsConstructor;
import org.example.matrixspringapp165.dao.repository.PaymentRepository;
import org.example.matrixspringapp165.mapper.PaymentMapper;
import org.example.matrixspringapp165.model.PaymentDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    
    public void createPayment(PaymentDto paymentDto) {
        var paymentEntity = paymentMapper.mapToEntity(paymentDto);
        paymentRepository.save(paymentEntity);
    }
}
