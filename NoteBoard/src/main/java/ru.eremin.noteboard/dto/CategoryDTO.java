package ru.eremin.noteboard.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.eremin.noteboard.entity.Board;
import ru.eremin.noteboard.entity.Category;

import java.io.Serializable;
import java.util.Objects;

/**
 * @autor Artem Eremin on 16.12.2018.
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CategoryDTO extends AbstractDTO implements Serializable {

    private String id;
    private String name;
    private String boardId;

    public CategoryDTO(final Category category) {
        if (category == null) return;
        this.id = category.getId();
        this.name = category.getName();
        final Board board = category.getBoard();
        if (board != null) this.boardId = category.getBoard().getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryDTO that = (CategoryDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
