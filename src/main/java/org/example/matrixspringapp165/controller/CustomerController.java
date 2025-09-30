package org.example.matrixspringapp165.controller;

import lombok.RequiredArgsConstructor;
import org.example.matrixspringapp165.model.CustomerDto;
import org.example.matrixspringapp165.service.CustomerService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/{id}")
    public CustomerDto getCustomer(@PathVariable Long id) {
        return customerService.getCustomer(id);
    }

    @PostMapping
    public void addCustomer(@RequestBody CustomerDto customerDto) {
        customerService.addCustomer(customerDto);
    }

    @DeleteMapping("/{customerId}/orders/{orderId}")
    public void removeCustomerOrders(
            @PathVariable Long customerId,
            @PathVariable Long orderId
    ) {
        customerService.removeCustomerOrders(customerId, orderId);
    }

    @DeleteMapping("/{customerId}")
    public void removeCustomerOrders(
            @PathVariable Long customerId
    ) {
        customerService.deleteCustomer(customerId);
    }
}
