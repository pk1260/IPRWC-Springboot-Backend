package org.example.iprwcspringbootbackend.dao;

import lombok.RequiredArgsConstructor;
import org.example.iprwcspringbootbackend.model.Product;
import org.example.iprwcspringbootbackend.model.Size;
import org.example.iprwcspringbootbackend.repository.ProductRepository;
import org.example.iprwcspringbootbackend.repository.SizeRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SizeDAO {
    private final SizeRepository sizeRepository;
    private final ProductRepository productRepository;

    public Size findById(UUID id) {
        return sizeRepository.findById(id).orElse(null);
    }
    public Size save(Size size) {
        return sizeRepository.save(size);
    }

    public Size createSizeForProduct(UUID productId, int size, int stock) {
        Product product = productRepository.findById(productId).orElse(null);

        if(product == null) return null;

        Size newSize = new Size();
        newSize.setSize(size);
        newSize.setStock(stock);
        newSize.setProduct(product);
        product.getSizes().add(newSize);
        productRepository.save(product);
        return newSize;
    }



}
