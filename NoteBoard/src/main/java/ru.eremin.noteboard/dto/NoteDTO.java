package ru.eremin.noteboard.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import ru.eremin.noteboard.entity.*;

import javax.xml.bind.annotation.XmlRootElement;
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
@XmlRootElement
@NoArgsConstructor
public class NoteDTO extends AbstractDTO implements Serializable {

    private String id;
    private NoteStatus status;
    private String authorId;
    private String data;
    private String categoryId;
    private String boardId;
    private NoteType type;
    private String pictureId;
    private Calendar noteDeadline;
    private List<String> commentsId;

    public NoteDTO(final Note note) {
        if (note == null) return;
        this.id = note.getId();
        if (note.getStatus() != null) this.status = note.getStatus();
        if (note.getType() != null) this.type = note.getType();
        if (note.getAuthor() != null) this.authorId = note.getAuthor().getId();
        if (note.getData() != null) this.data = note.getData();
        if (note.getCategory() != null) this.categoryId = note.getCategory().getId();
        if (note.getBoard() != null) this.boardId = note.getBoard().getId();
        if (note.getPicture() != null) this.pictureId = note.getPicture().getId();
        if (note.getDeadline() != null) this.noteDeadline = note.getDeadline();
        final List<Comment> comments = note.getComments();
        if (comments != null && !comments.isEmpty()) {
            this.commentsId = new ArrayList<>();
            for (final Comment comment : comments) {
                commentsId.add(comment.getId());
            }
        }
    }

    @NotNull
    public String getDeadlineDate() {
        return this.noteDeadline == null ? "" : this.noteDeadline.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoteDTO noteDTO = (NoteDTO) o;
        return Objects.equals(id, noteDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
