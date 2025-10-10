package org.example.matrixspringapp165.service.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.example.matrixspringapp165.dao.entity.OrderEntity;
import org.springframework.data.jpa.domain.Specification;

public class OrderNumberSpecification implements Specification<OrderEntity> {

    private final String orderNumber;

    public OrderNumberSpecification(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Override
    public Predicate toPredicate(Root<OrderEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (orderNumber == null || orderNumber.isBlank()) {
            return criteriaBuilder.conjunction();
        }

        return criteriaBuilder.equal(root.get("orderNumber"), orderNumber);
    }
}
