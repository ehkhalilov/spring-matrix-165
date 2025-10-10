package org.example.matrixspringapp165.controller;


import jakarta.validation.constraints.Max;
import org.example.matrixspringapp165.model.OrderDto;
import org.example.matrixspringapp165.service.OrderService;
import org.example.matrixspringapp165.validation.OnCreate;
import org.example.matrixspringapp165.validation.OnUpdate;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
@Validated
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public Page<OrderDto> getOrders(@ParameterObject
                                    @PageableDefault(page = 0, size = 5, sort = "orderNumber", direction = Sort.Direction.DESC)
                                    Pageable pageable,
                                    @RequestParam String orderNumber) {
        return orderService.getOrders(pageable, orderNumber);
    }

    @GetMapping("/{orderId}")
    public OrderDto getOrder(@PathVariable @Max(100) Long orderId) {
        return orderService.getOrder(orderId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addOrder(
            @RequestHeader Long customerId,
            @RequestBody @Validated(OnCreate.class) OrderDto orderDto
    ) {
        orderService.addOrder(orderDto, customerId);
    }

    @PutMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editOrder(@Validated(OnUpdate.class) @RequestBody OrderDto orderEntity, @PathVariable Long orderId) {
        orderService.editOrder(orderEntity, orderId);
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(
            @PathVariable Long orderId
    ) {
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
