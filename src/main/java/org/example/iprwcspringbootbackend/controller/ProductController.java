package org.example.iprwcspringbootbackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.iprwcspringbootbackend.dao.ProductDAO;
import org.example.iprwcspringbootbackend.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductDAO productDAO;

    @PostMapping(value = "/create")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productDAO.create(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") String id) {
        Product deletedProduct = productDAO.delete(UUID.fromString(id));
        if (deletedProduct == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(deletedProduct, HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<Iterable<Product>> getAllProducts() {
        Iterable<Product> products = productDAO.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


}
