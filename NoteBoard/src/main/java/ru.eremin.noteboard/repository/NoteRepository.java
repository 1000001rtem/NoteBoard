package ru.eremin.noteboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.eremin.noteboard.entity.*;

import java.util.List;

/**
 * @autor Artem Eremin on 16.12.2018.
 */

@Repository(NoteRepository.NAME)
public interface NoteRepository extends JpaRepository<Note, String> {

    String NAME = "noteRepository";

    Note findNoteById(String id);

    List<Note> findNotesByBoardId(String id);

    List<Note> findNotesByAuthor(User author);

    List<Note> findNotesByCategory(Category category);

    List<Note> findNotesByCategoryId(String categoryId);

    List<Note> findNotesByBoard(Board board);

}
