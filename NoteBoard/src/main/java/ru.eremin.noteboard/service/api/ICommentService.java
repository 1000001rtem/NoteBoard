package ru.eremin.noteboard.service.api;

import ru.eremin.noteboard.dto.CommentDTO;
import ru.eremin.noteboard.dto.NoteDTO;
import ru.eremin.noteboard.dto.UserDTO;
import ru.eremin.noteboard.entity.Comment;

import java.util.List;

/**
 * @autor Artem Eremin on 16.12.2018.
 */

public interface ICommentService extends IService<CommentDTO, Comment> {

    List<CommentDTO> findCommentsByAuthor(final UserDTO authorDTO);

    List<CommentDTO> findCommentsByNote(final NoteDTO noteDTO);

}
