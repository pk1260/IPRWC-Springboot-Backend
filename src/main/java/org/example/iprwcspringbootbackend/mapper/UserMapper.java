package org.example.iprwcspringbootbackend.mapper;

import lombok.RequiredArgsConstructor;
import org.example.iprwcspringbootbackend.dto.UserCreateDTO;
import org.example.iprwcspringbootbackend.dto.UserResponseDTO;
import org.example.iprwcspringbootbackend.model.User;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {
    public User toEntity(UserCreateDTO userCreateDTO) {
        return User
                .builder()
                .username(userCreateDTO.getUsername())
                .password(userCreateDTO.getPassword())
                .build();
    }

    public UserResponseDTO fromEntity(User user) {
        return UserResponseDTO
                .builder()
                .id(user.getId())
                .username(user.getUsername())
                .cart(user.getCart())
                .role(user.getRole())
                .build();
    }
}
