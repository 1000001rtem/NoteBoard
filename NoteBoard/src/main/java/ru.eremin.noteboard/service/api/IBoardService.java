package ru.eremin.noteboard.service.api;

import ru.eremin.noteboard.dto.BoardDTO;
import ru.eremin.noteboard.entity.Board;
import ru.eremin.noteboard.entity.User;

import java.util.List;

/**
 * @autor Artem Eremin on 16.12.2018.
 */

public interface IBoardService extends IService<BoardDTO, Board> {

    List<BoardDTO> findByAuthorId(final String authorId);

    List<BoardDTO> findByAuthor(final User author);

}
