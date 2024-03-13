package org.example.iprwcspringbootbackend.repository;

import org.example.iprwcspringbootbackend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {}
