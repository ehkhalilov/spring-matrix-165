package org.example.matrixspringapp165.service;

import lombok.RequiredArgsConstructor;
import org.example.matrixspringapp165.dao.repository.CustomerRepository;
import org.example.matrixspringapp165.mapper.CustomerMapper;
import org.example.matrixspringapp165.model.CustomerDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerDto getCustomer(Long id) {
        var entity = customerRepository.findById(id).orElseThrow();
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

    public void deleteCustomer(Long customerId) {
        var customerEntity = customerRepository.findById(customerId).orElseThrow();
        customerRepository.delete(customerEntity);
    }
}
