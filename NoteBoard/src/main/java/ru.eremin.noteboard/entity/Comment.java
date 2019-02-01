package ru.eremin.noteboard.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * @autor Artem Eremin on 16.12.2018.
 */

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "comment_table")
public class Comment extends AbstractEntity implements Serializable {

    @Id
    private String id = UUID.randomUUID().toString();

    @Column(name = "comment_text")
    private String text;

    @Column(name = "comment_date")
    private Calendar date;

    @ManyToOne
    private User author;

    @ManyToOne
    private Note note;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
