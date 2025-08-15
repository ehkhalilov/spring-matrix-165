package org.example.matrixspringapp165.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
//    List<OrderEntity> findByOrderNumberOrQuantityOrderByIdDesc(String orderNumber, Integer quantity);


//    @Query(value = "select * from orders where order_number = ? or quantity = ?", nativeQuery = true)
//    List<OrderEntity> getOrders(String orderNumber, Integer quantity);

    @Query(value = "select o from OrderEntity o where o.orderNumber = :orderNumber or o.quantity = :quantity")
    List<OrderEntity> getOrders(String orderNumber, Integer quantity);

    @Transactional
    @Modifying
    @Query(value = "update orders set quantity = ? where id = ?", nativeQuery = true)
    void updateQuantity(Integer quantity, Long orderId);
}

// select * from orders where order_number = ? or quantity = ?
