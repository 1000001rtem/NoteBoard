package ru.eremin.noteboard.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.eremin.noteboard.dto.UserDTO;
import ru.eremin.noteboard.entity.Role;
import ru.eremin.noteboard.entity.RoleType;
import ru.eremin.noteboard.entity.User;
import ru.eremin.noteboard.repository.UserRepository;
import ru.eremin.noteboard.service.api.IUserService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @autor Artem Eremin on 20.12.2018.
 */

@Service(UserService.NAME)
public class UserService implements IUserService {

    public static final String NAME = "userService";

    @Autowired
    private UserRepository repository;

    @Override
    @Nullable
    @Transactional(readOnly = true)
    public UserDTO findUserByEmail(@Nullable final String email) {
        if (email == null || email.isEmpty()) return null;
        final User user = repository.findUserByEmail(email);
        if (user == null) return null;
        return new UserDTO(user);
    }

    @Override
    @Nullable
    @Transactional(readOnly = true)
    public UserDTO findUserByLogin(@Nullable final String login) {
        if (login == null || login.isEmpty()) return null;
        final User user = repository.findUserByLogin(login);
        if (user == null) return null;
        return new UserDTO(user);
    }

    @Override
    @Nullable
    @Transactional(readOnly = true)
    public List<UserDTO> findAll() {
        final List<User> userList = repository.findAll();
        if (userList == null || userList.isEmpty()) return null;
        return userList.stream().map(UserDTO::new).collect(Collectors.toList());
    }

    @Override
    @Nullable
    @Transactional(readOnly = true)
    public List<UserDTO> findAll(final int page, final int size) {
        final List<User> userList = repository.findAll(PageRequest.of(page, size)).getContent();
        if (userList == null || userList.isEmpty()) return null;
        return userList.stream().map(UserDTO::new).collect(Collectors.toList());
    }

    @Override
    @Nullable
    @Transactional(readOnly = true)
    public UserDTO findById(@Nullable final String id) {
        if (id == null || id.isEmpty()) return null;
        final User user = repository.findUserById(id);
        if (user == null) return null;
        return new UserDTO(user);
    }

    @Override
    @Transactional
    public void initUser(@NotNull final String login,
                         @NotNull final String password,
                         @NotNull final String email,
                         @NotNull final RoleType roleType) {
        if (findAll() != null) return;
        final User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setLogin(login);
        user.setEmail(email);
        user.setHashPassword(password);
        final List<Role> roles = new ArrayList<>();
        roles.add(new Role(UUID.randomUUID().toString(), user, roleType));
        user.setRoleList(roles);
        repository.save(user);
    }

    @Override
    @Transactional
    public void insert(@Nullable final UserDTO userDTO) {
        if (userDTO == null) return;
        final User user = getEntity(userDTO);
        if (repository.findUserById(userDTO.getId()) != null) return;
        repository.save(user);
    }

    @Override
    @Transactional
    public void merge(@Nullable final UserDTO userDTO) {
        if (userDTO == null) return;
        final User user = getEntity(userDTO);
        repository.save(user);
    }

    @Override
    @Transactional
    public void deleteById(@Nullable final String id) {
        if (id != null && !id.isEmpty()) repository.deleteById(id);
    }

    @Override
    @Transactional
    public void delete(@Nullable final UserDTO userDTO) {
        if (userDTO == null) return;
        final User user = getEntity(userDTO);
        repository.delete(user);
    }

    @Override
    @Transactional
    public void update(@Nullable final UserDTO userDTO) throws IllegalArgumentException {
        if (userDTO == null) return;
        if (userDTO.getId() == null || userDTO.getId().isEmpty()) return;
        final User user = repository.findUserById(userDTO.getId());
        if (user == null) return;
        if (!user.getLogin().equals(userDTO.getLogin())
                && repository.findUserByLogin(userDTO.getLogin()) != null)
            throw new IllegalArgumentException("Login exist");
        if (!user.getEmail().equals(userDTO.getEmail())
                && repository.findUserByEmail(userDTO.getEmail()) != null)
            throw new IllegalArgumentException("Email exist");
        user.setLogin(user.getLogin());
        user.setEmail(userDTO.getEmail());
        repository.save(user);
    }

    @Override
    @NotNull
    public User getEntity(@NotNull final UserDTO userDTO) {
        final User user = new User();
        user.setId(userDTO.getId());
        user.setEmail(userDTO.getEmail());
        user.setLogin(userDTO.getLogin());
        user.setHashPassword(userDTO.getHashPassword());
        final List<RoleType> roleTypes = userDTO.getRoles();
        final List<Role> roles = new ArrayList<>();
        if (roleTypes != null) {
            for (final RoleType roleType : roleTypes) {
                roles.add(new Role(UUID.randomUUID().toString(), user, roleType));
            }
        }
        user.setRoleList(roles);
        return user;
    }
}
