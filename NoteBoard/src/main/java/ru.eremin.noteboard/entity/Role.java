package ru.eremin.noteboard.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

/**
 * @autor Artem Eremin on 30.12.2018.
 */

@Entity
@Getter
@Setter
//@ToString
@NoArgsConstructor
@Table(name = "role_table")
public class Role extends AbstractEntity {

    @Id
    private String id = UUID.randomUUID().toString();

    @ManyToOne
    private User user;

    @Column
    @Enumerated(EnumType.STRING)
    private RoleType roleType = RoleType.USER;

    public Role(final String id, final User user, final RoleType roleType) {
        this.id = id;
        this.user = user;
        this.roleType = roleType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id) &&
                roleType == role.roleType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleType);
    }

    @Override
    public String toString() {
        return roleType.name();
    }
}
