package org.example.service;

import org.example.entity.User;

import java.util.Optional;

public interface UserService {
    User register(User user);
    Optional<User> login(String usernameOrEmail, String password);
}
