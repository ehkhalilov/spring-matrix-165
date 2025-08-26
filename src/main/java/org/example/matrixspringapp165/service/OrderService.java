package org.example.matrixspringapp165.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.example.matrixspringapp165.dao.OrderRepository;
import org.example.matrixspringapp165.mapper.OrderMapper;
import org.example.matrixspringapp165.model.OrderDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileWriter;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public List<OrderDto> getOrders() {
        log.info("getOrders start");
        var orderEntityList = orderRepository.findAll();
        var orders = orderEntityList.stream().map(orderMapper::mapToDto).toList();
        log.info("getOrders end");
        return orders;
    }

    public OrderDto getOrder(Long id) {
        log.debug("ActionLog.getOrder start for id {}", id);
        var entity = orderRepository.findById(id).orElseThrow(
                () -> {
                    log.error("Order not found by id {}", id);
                    return new RuntimeException();
                }
        );
        var order = orderMapper.mapToDto(entity);
        log.info("ActionLog.getOrder end for id {}", id);
        return order;
    }

    @SneakyThrows
    public void addOrder(OrderDto orderDto) {
        var orderEntity = orderMapper.mapToEntity(orderDto);
        orderRepository.save(orderEntity);

        var order1 = OrderDto.builder().number("1").quantity(3).build();

        val fileWriter = new FileWriter("test.txt");


//        var order2 = new OrderDto();
//        order2.setQuantity(3);
//        order2.setNumber("2");
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
