package org.example.matrixspringapp165.dao.repository;

import org.example.matrixspringapp165.dao.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
}
