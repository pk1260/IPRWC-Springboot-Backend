package org.example.iprwcspringbootbackend.service;

import lombok.RequiredArgsConstructor;
import org.example.iprwcspringbootbackend.dao.UserDAO;
import org.example.iprwcspringbootbackend.model.Cart;
import org.example.iprwcspringbootbackend.model.Role;
import org.example.iprwcspringbootbackend.model.User;
import org.example.iprwcspringbootbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public Optional<String> register(String username, String password) {
        Optional<org.example.iprwcspringbootbackend.model.User> foundUser = userDAO.findByUsername(username);
        if (foundUser.isPresent()) {
            return Optional.empty();
        }

        Cart cart = new Cart();

        User user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .role(Role.CUSTOMER)
                .cart(cart)
                .build();

        userDAO.save(user);
        String token = jwtService.generateToken(Map.of("role", user.getRole()), user.getId() );
        return Optional.of(token);
    }
    public Role getRoleByUsername(String username) {
        User user = this.userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            return null;
        }
        return user.getRole();
    }


    public String login(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        User user = userDAO.loadUserByUsername(username);
        return jwtService.generateToken(Map.of("role", user.getRole()), user.getId());
    }
}
