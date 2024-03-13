package org.example.iprwcspringbootbackend.repository;

import org.example.iprwcspringbootbackend.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, UUID> {
    Optional<Cart> findCartByUserId(UUID userId);
}
