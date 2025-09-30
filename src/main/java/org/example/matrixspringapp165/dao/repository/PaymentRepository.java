package org.example.matrixspringapp165.dao.repository;

import org.example.matrixspringapp165.dao.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
}
