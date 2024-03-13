package org.example.iprwcspringbootbackend.seeder;

import lombok.RequiredArgsConstructor;
import org.example.iprwcspringbootbackend.dao.UserDAO;
import org.example.iprwcspringbootbackend.model.Role;
import org.example.iprwcspringbootbackend.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;


@Component
@RequiredArgsConstructor
public class AdminSeeder {
    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;
    private final Logger logger;

    @Value("${admin.name}")
    private String adminName;

    @Value("${admin.password}")
    private String adminPassword;

    public void seed() {
        var admin = User.builder()
                .username(adminName)
                .password(passwordEncoder.encode(adminPassword))
                .role(Role.ADMIN)
                .build();
        try {
            this.userDAO.save(admin);
        } catch (Exception e) {
            logger.warn("couldn't create admin account: " + e.getMessage());
        }
    }
}