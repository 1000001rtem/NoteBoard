package ru.eremin.noteboard.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * @autor Artem Eremin on 16.12.2018.
 */

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "board_table")
public class Board extends AbstractEntity implements Serializable {

    @Id
    private String id = UUID.randomUUID().toString();

    @Column(name = "board_name", nullable = false)
    private String name = "";

    @Temporal(TemporalType.DATE)
    @Column(name = "create_date", nullable = false)
    private Calendar date;

    @ManyToOne
    private User author;

    @OneToMany(mappedBy = "board", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Note> notes;

    @OneToMany(mappedBy = "board", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Category> categories;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return Objects.equals(id, board.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
