package ru.eremin.noteboard.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.eremin.noteboard.entity.Board;
import ru.eremin.noteboard.entity.Category;
import ru.eremin.noteboard.entity.Note;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

/**
 * @autor Artem Eremin on 16.12.2018.
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDTO extends AbstractDTO implements Serializable {

    private String id;
    private String name;
    private Calendar date;
    private String authorId;
    private List<String> noteIds;
    private List<String> categoryIds;

    public BoardDTO(final Board board) {
        if (board == null) return;
        this.id = board.getId();
        this.name = board.getName();
        this.date = board.getDate();
        this.authorId = board.getAuthor().getId();
        this.noteIds = new ArrayList<>();
        final List<Note> notes = board.getNotes();
        if (notes != null && notes.isEmpty()) {
            for (final Note note : notes) {
                this.noteIds.add(note.getId());
            }
        }
        this.categoryIds = new ArrayList<>();
        final List<Category> categories = board.getCategories();
        for (final Category category : categories) {
            this.categoryIds.add(category.getId());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardDTO boardDTO = (BoardDTO) o;
        return Objects.equals(id, boardDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
