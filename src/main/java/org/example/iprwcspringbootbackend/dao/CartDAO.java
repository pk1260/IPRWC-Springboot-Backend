package org.example.iprwcspringbootbackend.dao;

import lombok.RequiredArgsConstructor;
import org.example.iprwcspringbootbackend.model.Cart;
import org.example.iprwcspringbootbackend.model.CartItem;
import org.example.iprwcspringbootbackend.model.Size;
import org.example.iprwcspringbootbackend.repository.CartItemRepository;
import org.example.iprwcspringbootbackend.repository.CartRepository;
import org.example.iprwcspringbootbackend.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CartDAO {
    private final SizeDAO sizeDAO;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;

    public boolean isProductInStock(CartItem cartItem) {
        Size size = sizeDAO.findById(cartItem.getSize().getId());
        if (size == null) {
            throw new IllegalArgumentException("No Size found with id: " + cartItem.getSize());
        }
        return size.getStock() != 0 && size.getStock() >= cartItem.getQuantity();
    }

    public Cart getCartByUserId(UUID userId) {
        return cartRepository.findCartByUserId(userId).orElse(null);
    }

    public void addItemToCart(UUID userId, CartItem cartItem) {
        Cart cart = cartRepository.findCartByUserId(userId).orElse(null);
        if (cart == null) {
            throw new IllegalArgumentException("No Cart found with id: " + userId);
        }
        cartItem.setCart(cart);
        cart.getCartItems().add(cartItem);
        cartRepository.save(cart);
    }

    public void clearCart(UUID userId) {
        Cart cart = cartRepository.findCartByUserId(userId).orElse(null);
        if (cart == null) {
            throw new IllegalArgumentException("No Cart found with id: " + userId);
        }
        System.out.println(cart.getCartItems().size());
        cart.getCartItems().clear();
        cartRepository.save(cart);
    }
}
