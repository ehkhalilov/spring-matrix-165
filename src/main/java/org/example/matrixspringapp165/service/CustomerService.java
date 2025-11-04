package org.example.matrixspringapp165.service;

import lombok.RequiredArgsConstructor;
import org.example.matrixspringapp165.dao.repository.CustomerRepository;
import org.example.matrixspringapp165.dao.repository.ProductRepository;
import org.example.matrixspringapp165.exception.NotFoundException;
import org.example.matrixspringapp165.mapper.CustomerMapper;
import org.example.matrixspringapp165.model.CustomerDto;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final ProductRepository productRepository;

    public CustomerDto getCustomer(Long id) {
        var entity = customerRepository.findById(id).orElseThrow(
                () -> new NotFoundException("CUSTOMER_NOT_FOUND")
        );

        return customerMapper.entityToDto(entity);
    }

    public void addCustomer(CustomerDto customerDto) {
        var entity = customerMapper.dtoToEntity(customerDto);
        customerRepository.save(entity);
    }

    public void removeCustomerOrders(
            Long customerId, Long orderId
    ) {
        var customerEntity = customerRepository.findById(customerId).orElseThrow();
        customerEntity.getOrders()
                .removeIf(orderEntity -> orderEntity.getId().equals(orderId));
        customerRepository.save(customerEntity);
    }

    @Async
    public void deleteCustomer(Long customerId) {
        var customerEntity = customerRepository.findById(customerId).orElseThrow();
        customerRepository.delete(customerEntity);
    }

    @Transactional
    public void addProductToCustomer(Long customerId, Long productId) {
        var productEntity = productRepository.findById(productId).orElseThrow();
        var customerEntity = customerRepository.findById(customerId).orElseThrow();
        customerEntity.getProducts().add(productEntity);
        customerRepository.deleteById(1L);
        customerRepository.save(customerEntity);
    }

    public int add(int a, int b) {
        return a + b;
    }
}
