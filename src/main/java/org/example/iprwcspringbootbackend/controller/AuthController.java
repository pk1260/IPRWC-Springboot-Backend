package org.example.iprwcspringbootbackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.iprwcspringbootbackend.dto.AuthRequestDTO;
import org.example.iprwcspringbootbackend.dto.AuthResponseDTO;
import org.example.iprwcspringbootbackend.dto.RegisterResponseDTO;
import org.example.iprwcspringbootbackend.model.User;
import org.example.iprwcspringbootbackend.repository.UserRepository;
import org.example.iprwcspringbootbackend.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;

    @PostMapping(value = "/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO loginDTO) {
        String token = authenticationService.login(loginDTO.getUsername(), loginDTO.getPassword());
        String role = authenticationService.getRoleByUsername(loginDTO.getUsername()).name();
        Optional<User> user = userRepository.findByUsername(loginDTO.getUsername());
        if (user.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        UUID id = user.get().getId();
        AuthResponseDTO response = new AuthResponseDTO(token, role, loginDTO.getUsername(), id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody AuthRequestDTO registerDTO) {
        Optional<String> token = authenticationService.register(registerDTO.getUsername(), registerDTO.getPassword());
        if (!token.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String role = authenticationService.getRoleByUsername(registerDTO.getUsername()).name();
        RegisterResponseDTO response = new RegisterResponseDTO(token.get(), role, registerDTO.getUsername());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
