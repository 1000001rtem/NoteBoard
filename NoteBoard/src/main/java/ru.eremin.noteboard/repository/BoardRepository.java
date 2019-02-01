package ru.eremin.noteboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.eremin.noteboard.entity.Board;
import ru.eremin.noteboard.entity.User;

import java.util.List;

/**
 * @autor Artem Eremin on 16.12.2018.
 */

@Repository(BoardRepository.NAME)
public interface BoardRepository extends JpaRepository<Board, String> {

    String NAME = "boardRepository";

    Board findBoardById(String id);

    List<Board> findBoardsByAuthor(User author);

    List<Board> findBoardsByAuthorId(String authorId);

}
