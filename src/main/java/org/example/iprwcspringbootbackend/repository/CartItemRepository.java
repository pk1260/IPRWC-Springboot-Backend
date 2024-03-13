package org.example.iprwcspringbootbackend.repository;

import org.example.iprwcspringbootbackend.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CartItemRepository extends JpaRepository<CartItem, UUID> {
    Optional<CartItem> deleteCartItemsByCartId(UUID cartId);
    Optional<CartItem> findCartItemByCartId(UUID cartId);
    Optional<CartItem> deleteAllByCartId(UUID cartId);
}
