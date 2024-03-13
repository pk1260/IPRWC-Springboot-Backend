package org.example.iprwcspringbootbackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.iprwcspringbootbackend.dao.CartDAO;
import org.example.iprwcspringbootbackend.model.Cart;
import org.example.iprwcspringbootbackend.model.CartItem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartDAO cartDAO;

    @PostMapping(value = "/order/{userId}")
    public ResponseEntity<CartItem> placeOrder(@PathVariable UUID userId){
        Cart cart = cartDAO.getCartByUserId(userId);
        int counter = 0;
        for (CartItem item : cart.getCartItems()) {
            if (item.getId()== null) {
                return ResponseEntity.badRequest().build();
            }
            if (!cartDAO.isProductInStock(item.getCart().getCartItems().get(counter))) {
                return ResponseEntity.badRequest().build();
            }
//            cartDAO.updateStock(item);
            counter ++;
        }
        cartDAO.clearCart(userId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Cart> addItemToCart(@PathVariable UUID userId, @RequestBody CartItem cartItem) {
        Cart cart = cartDAO.getCartByUserId(userId);
        if (cart == null) {
            return ResponseEntity.notFound().build();
        }
        cartDAO.addItemToCart(userId, cartItem);
        return ResponseEntity.ok(cart);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Cart> getCartByUserId(@PathVariable UUID userId) {
        Cart cart = cartDAO.getCartByUserId(userId);

        if (cart == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Cart> deleteCartByUserId(@PathVariable UUID userId) {
        Cart cart = cartDAO.getCartByUserId(userId);
        if (cart == null) {
            return ResponseEntity.notFound().build();
        }
        cartDAO.clearCart(userId);
        return ResponseEntity.ok().build();
    }
}
