package org.example.matrixspringapp165.controller;


import org.example.matrixspringapp165.dao.OrderEntity;
import org.example.matrixspringapp165.model.OrderDto;
import org.example.matrixspringapp165.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<OrderDto> getOrders() {
        return orderService.getOrders();
    }

    @GetMapping("/{orderId}")
    public OrderDto getOrder(@PathVariable Long orderId) {
        return orderService.getOrder(orderId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addOrder(@RequestBody OrderDto orderDto) {
        orderService.addOrder(orderDto);
    }

    @PutMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editOrder(@RequestBody OrderDto orderEntity, @PathVariable Long orderId) {
        orderService.editOrder(orderEntity, orderId);
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
    }

    @GetMapping("/order-number/{orderNumber}")
    public List<OrderDto> getOrderByNumber(
            @PathVariable String orderNumber,
            @RequestParam Integer quantity
    ) {
        return orderService.getOrderByNumber(orderNumber, quantity);
    }

    @PatchMapping("/{orderId}/quantity")
    public void changeOrderQuantity(
            @PathVariable Long orderId,
            @RequestBody Integer quantity
    ) {
        orderService.changeOrderQuantity(orderId, quantity);
    }
}
