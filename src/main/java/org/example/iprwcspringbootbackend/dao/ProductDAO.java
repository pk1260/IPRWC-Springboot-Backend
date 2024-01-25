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

    public Product create(Product product) {
        return productRepository.save(product);
    }

    public Product delete(UUID id) {
        Product productToDelete = productRepository.findById(id).orElse(null);
        if (productToDelete == null) return null;
        productRepository.delete(productToDelete);
        return productToDelete;
    }

}
