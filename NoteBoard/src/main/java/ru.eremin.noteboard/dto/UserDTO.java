package ru.eremin.noteboard.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.eremin.noteboard.entity.Role;
import ru.eremin.noteboard.entity.RoleType;
import ru.eremin.noteboard.entity.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @autor Artem Eremin on 16.12.2018.
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDTO extends AbstractDTO implements Serializable {

    private String id;
    private String login;
    private String hashPassword;
    private String email;
    private List<RoleType> roles;

    public UserDTO(final User user) {
        if (user == null) return;
        this.id = user.getId();
        this.login = user.getLogin();
        this.hashPassword = user.getHashPassword();
        this.email = user.getEmail();
        this.roles = new ArrayList<>();
        final List<Role> roleList = user.getRoleList();
        if (roleList == null || roleList.isEmpty()) return;
        for (final Role role : roleList) {
            if (role != null) this.roles.add(role.getRoleType());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(id, userDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
