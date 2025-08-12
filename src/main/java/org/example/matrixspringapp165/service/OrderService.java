package org.example.matrixspringapp165.service;

import org.example.matrixspringapp165.dao.OrderEntity;
import org.example.matrixspringapp165.dao.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderEntity> getOrders() {
        return orderRepository.findAll();
    }

    public OrderEntity getOrder(Long id) {
        return orderRepository.findById(id).orElseThrow();
    }

    public void addOrder(OrderEntity orderEntity) {
        orderRepository.save(orderEntity);
    }

    public void editOrder(OrderEntity orderEntity, Long id) {
        var order = orderRepository.findById(id).orElseThrow();
        order.setOrderNumber(orderEntity.getOrderNumber());
        order.setQuantity(orderEntity.getQuantity());
        orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
