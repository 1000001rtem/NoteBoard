package ru.eremin.noteboard.service.security;

import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.eremin.noteboard.entity.Role;
import ru.eremin.noteboard.entity.User;
import ru.eremin.noteboard.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @autor Artem Eremin on 30.12.2018.
 */

@Service(UserDetailsServiceBean.NAME)
public class UserDetailsServiceBean implements UserDetailsService {

    public static final String NAME = "userDetailsService";

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        final User user = findByLogin(login);
        if (user == null) throw new UsernameNotFoundException("User not found");
        org.springframework.security.core.userdetails.User.UserBuilder builder = null;
        builder = org.springframework.security.core.userdetails.User.withUsername(login);
        builder.password(user.getHashPassword());
        final List<Role> userRoles = user.getRoleList();
        final List<String> roles = new ArrayList<>();
        for (final Role role : userRoles) roles.add(role.toString());
        builder.roles(roles.toArray(new String[]{}));
        UserDetails userDetails = builder.build();
        return userDetails;
    }

    private User findByLogin(@Nullable final String login) {
        if (login == null || login.isEmpty()) return null;
        return repository.findUserByLogin(login);
    }
}
