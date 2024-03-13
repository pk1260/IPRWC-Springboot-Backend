package org.example.iprwcspringbootbackend.dao;

import lombok.RequiredArgsConstructor;
import org.example.iprwcspringbootbackend.model.CartItem;
import org.example.iprwcspringbootbackend.model.Size;
import org.example.iprwcspringbootbackend.repository.CartRepository;
import org.example.iprwcspringbootbackend.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CartDAO {
    private final SizeDAO sizeDAO;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    public boolean isProductInStock(CartItem cartItem){
//        Size size = sizeDAO.findById(cartItem.getSize();


        return false;
    }
}
