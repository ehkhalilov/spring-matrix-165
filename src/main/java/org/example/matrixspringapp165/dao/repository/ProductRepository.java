package org.example.matrixspringapp165.dao.repository;

import org.example.matrixspringapp165.dao.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
