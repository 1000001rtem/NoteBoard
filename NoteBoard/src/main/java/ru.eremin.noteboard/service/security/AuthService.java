package ru.eremin.noteboard.service.security;

import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.eremin.noteboard.entity.User;
import ru.eremin.noteboard.repository.UserRepository;

import java.security.Principal;

/**
 * @autor Artem Eremin on 08.01.2019.
 */

@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public AuthService(final PasswordEncoder passwordEncoder, final UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Nullable
    public User getUser(@Nullable final Principal principal) {
        if (principal == null) return null;
        final String login = principal.getName();
        return userRepository.findUserByLogin(login);
    }

}
