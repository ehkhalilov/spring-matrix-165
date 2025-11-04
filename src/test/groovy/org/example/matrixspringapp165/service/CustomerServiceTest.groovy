package org.example.matrixspringapp165.service

import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import org.example.matrixspringapp165.dao.entity.CustomerEntity
import org.example.matrixspringapp165.dao.repository.CustomerRepository
import org.example.matrixspringapp165.dao.repository.ProductRepository
import org.example.matrixspringapp165.exception.NotFoundException
import org.example.matrixspringapp165.mapper.CustomerMapper
import org.example.matrixspringapp165.model.CustomerDto
import spock.lang.Specification
import spock.lang.Unroll

import java.time.LocalDate

class CustomerServiceTest extends Specification {
    private CustomerRepository customerRepository
    private CustomerMapper customerMapper
    private ProductRepository productRepository
    private CustomerService customerService
    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()


    void setup() {
        customerRepository = Mock()
        customerMapper = Mock()
        productRepository = Mock()
        customerService = new CustomerService(customerRepository, customerMapper, productRepository)
    }

    def customerId = random.nextLong()

    def "GetCustomer success"() {
        given:
        def customerEntity = random.nextObject(CustomerEntity)
        def customerDto = random.nextObject(CustomerDto)

        when:
        def result = customerService.getCustomer(customerId)

        then:
        1 * customerRepository.findById(customerId) >> Optional.of(customerEntity)
        1 * customerMapper.entityToDto(customerEntity) >> customerDto

        result == customerDto
    }

    def "GetCustomer CUSTOMER_NOT_FOUND"() {
        when:
        def result = customerService.getCustomer(customerId)

        then:
        1 * customerRepository.findById(customerId) >> Optional.empty()
        0 * customerMapper.entityToDto(_)

        def exception = thrown(NotFoundException)
        exception.message == "CUSTOMER_NOT_FOUND"

        result == null
    }

    @Unroll
    def "sum of #a and #b should equal #expected"() {
        when:
        def actual = customerService.add(a, b)

        then:
        actual == expected

        where:
        a  | b  | expected
        1  | 2  | 3
        5  | 10 | 15
        -2 | 7  | 5
    }

}
