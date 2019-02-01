package ru.eremin.noteboard.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.eremin.noteboard.entity.Comment;
import ru.eremin.noteboard.entity.Note;
import ru.eremin.noteboard.entity.User;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * @autor Artem Eremin on 16.12.2018.
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CommentDTO extends AbstractDTO implements Serializable {

    private String id;
    private String text;
    private Calendar date;
    private String authorId;
    private String noteId;

    public CommentDTO(final Comment comment) {
        if (comment == null) return;
        this.id = comment.getId();
        this.text = comment.getText();
        this.date = comment.getDate();
        final User author = comment.getAuthor();
        if (author != null) this.authorId = author.getId();
        final Note note = comment.getNote();
        if (note != null) this.noteId = note.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentDTO that = (CommentDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
