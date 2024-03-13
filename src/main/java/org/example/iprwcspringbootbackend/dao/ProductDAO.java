package org.example.iprwcspringbootbackend.dao;

import lombok.RequiredArgsConstructor;
import org.example.iprwcspringbootbackend.model.Product;
import org.example.iprwcspringbootbackend.repository.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProductDAO {
    private final ProductRepository productRepository;
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(UUID id){
        return productRepository.findById(id).orElse(null);
    }

    public Product create(Product product) {
        return productRepository.save(product);
    }

    public Product delete(UUID id){
        Product product = productRepository.findById(id).orElse(null);
        productRepository.deleteById(id);
        return product;
    }
}
