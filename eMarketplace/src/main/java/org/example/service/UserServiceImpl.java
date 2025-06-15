package org.example.service;

import org.apache.commons.validator.routines.EmailValidator;
import org.example.entity.User;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User register(User user) {
        if (!user.getUsername().matches("^[a-zA-Z0-9]{8,20}$")) throw new IllegalArgumentException();
        if (!EmailValidator.getInstance().isValid(user.getEmail())) throw new IllegalArgumentException();
        if (Period.between(user.getBirthday(), LocalDate.now()).getYears() <= 13) throw new IllegalArgumentException();
        return repository.save(user);
    }

    @Override
    public Optional<User> login(String usernameOrEmail, String password) {
        return repository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .filter(user -> user.getPassword().equals(password));
    }
}
