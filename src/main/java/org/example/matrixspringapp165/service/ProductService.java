package org.example.matrixspringapp165.service;

import lombok.RequiredArgsConstructor;
import org.example.matrixspringapp165.dao.repository.ProductRepository;
import org.example.matrixspringapp165.mapper.ProductMapper;
import org.example.matrixspringapp165.model.ProductDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    public ProductDto getProduct(Long id) {
        var entity = productRepository.findById(id).orElseThrow();
        return productMapper.mapToDto(entity);
    }

    public void createProduct(ProductDto productDto) {
        var entity = productMapper.mapToEntity(productDto);
        productRepository.save(entity);
    }
}
