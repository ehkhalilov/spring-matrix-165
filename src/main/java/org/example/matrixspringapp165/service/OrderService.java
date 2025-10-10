package org.example.matrixspringapp165.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.example.matrixspringapp165.dao.entity.OrderEntity;
import org.example.matrixspringapp165.dao.repository.CustomerRepository;
import org.example.matrixspringapp165.dao.repository.OrderRepository;
import org.example.matrixspringapp165.exception.NotFoundException;
import org.example.matrixspringapp165.mapper.OrderMapper;
import org.example.matrixspringapp165.model.OrderDto;
import org.example.matrixspringapp165.service.specification.OrderNumberSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final OrderMapper orderMapper;

    public Page<OrderDto> getOrders(Pageable pageable, String orderNumber) {
        log.info("getOrders start");
        Specification<OrderEntity> specification = new OrderNumberSpecification(orderNumber);
        var orderEntityList = orderRepository.findAll(specification, pageable);
        var orders = orderEntityList.map(orderMapper::mapToDto);
        log.info("getOrders end");
        return orders;
    }

    public OrderDto getOrder(Long id) {
        log.debug("ActionLog.getOrder start for id {}", id);
        var entity = orderRepository.findById(id).orElseThrow(
                () -> {
                    log.error("Order not found by id {}", id);
                    return new NotFoundException("ORDER_NOT_FOUND");
                }
        );
        var order = orderMapper.mapToDto(entity);
        log.info("ActionLog.getOrder end for id {}", id);
        return order;
    }

    @SneakyThrows
    public void addOrder(OrderDto orderDto, Long customerId) {
        var orderEntity = orderMapper.mapToEntity(orderDto);
        var customerEntity = customerRepository.findById(customerId).orElseThrow();
        orderEntity.setCustomer(customerEntity);
        orderRepository.save(orderEntity);
    }

    public void editOrder(OrderDto orderDto, Long id) {
        var order = orderRepository.findById(id).orElseThrow();
        orderMapper.mapToEntityModify(order, orderDto);
        orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public List<OrderDto> getOrderByNumber(String orderNumber, Integer quantity) {
        var orderEntityList = orderRepository.getOrders(orderNumber, quantity);
        return orderEntityList.stream().map(orderMapper::mapToDto).toList();
    }

    @Transactional
    public void changeOrderQuantity(Long orderId, Integer quantity) {
        orderRepository.updateQuantity(quantity, orderId);
    }
}
