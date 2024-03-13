package org.example.iprwcspringbootbackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.iprwcspringbootbackend.dao.ProductDAO;
import org.example.iprwcspringbootbackend.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductDAO productDAO;


    @PostMapping(value = "/create")
    public ResponseEntity<UUID> createProduct(@RequestBody Product product) {
        Product createdProduct = productDAO.create(product);
        return new ResponseEntity<>(createdProduct.getId(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Iterable<Product>> getAllProducts() {
        Iterable<Product> products = productDAO.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @GetMapping(value = "/details/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") UUID id) {
        Product product = productDAO.findById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
