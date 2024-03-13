package org.example.iprwcspringbootbackend.seeder;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.example.iprwcspringbootbackend.dao.ProductDAO;
import org.example.iprwcspringbootbackend.model.Product;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;

@Component
@RequiredArgsConstructor
public class ProductSeeder {
    private final ProductDAO productDAO;
    private final Logger logger;

    @Value("${product.name}")
    private String productName;

    @Value("${product.description}")
    private String productDescription;

    @Value("${product.price}")
    private float productPrice;

    public void seed() {
        var product = Product.builder()
                .name(productName)
                .description(productDescription)
                .price(productPrice)
                .build();
        try {
            this.productDAO.create(product);
        } catch (Exception e) {
            logger.warn("couldn't create product: " + e.getMessage());
        }
    }
}
