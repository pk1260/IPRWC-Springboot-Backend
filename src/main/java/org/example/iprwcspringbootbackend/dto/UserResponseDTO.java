package org.example.iprwcspringbootbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.iprwcspringbootbackend.model.Cart;
import org.example.iprwcspringbootbackend.model.Role;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private UUID id;
    private String username;
    private String password;
    private Role role;
    private Cart cart;
}
