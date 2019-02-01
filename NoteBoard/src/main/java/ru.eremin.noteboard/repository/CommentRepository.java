package ru.eremin.noteboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.eremin.noteboard.entity.Comment;
import ru.eremin.noteboard.entity.Note;
import ru.eremin.noteboard.entity.User;

import java.util.List;

/**
 * @autor Artem Eremin on 16.12.2018.
 */

@Repository(CommentRepository.NAME)
public interface CommentRepository extends JpaRepository<Comment, String> {

    String NAME = "commentRepository";

    Comment findCommentById(String id);

    List<Comment> findCommentByAuthor(User author);

    List<Comment> findCommentsByNote(Note note);

}
