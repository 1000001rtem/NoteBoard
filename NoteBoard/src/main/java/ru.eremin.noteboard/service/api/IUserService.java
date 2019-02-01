package ru.eremin.noteboard.service.api;

import ru.eremin.noteboard.dto.UserDTO;
import ru.eremin.noteboard.entity.RoleType;
import ru.eremin.noteboard.entity.User;

/**
 * @autor Artem Eremin on 16.12.2018.
 */

public interface IUserService extends IService<UserDTO, User> {

    void initUser(final String login, final String password, final String email, final RoleType roleType);

    UserDTO findUserByEmail(final String email);

    UserDTO findUserByLogin(final String login);

}
