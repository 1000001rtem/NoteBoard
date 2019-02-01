package ru.eremin.noteboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.eremin.noteboard.entity.User;

/**
 * @autor Artem Eremin on 16.12.2018.
 */

@Repository(UserRepository.NAME)
public interface UserRepository extends JpaRepository<User, String> {

    String NAME = "userRepository";

    User findUserById(String Id);

    User findUserByEmail(String email);

    User findUserByLogin(String login);

}
