package ru.eremin.noteboard.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
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
@Table(name = "note_pickture_table")
public class NotePicture extends AbstractEntity implements Serializable {

    @Id
    private String id = UUID.randomUUID().toString();

    @Column(name = "picture_name", unique = true)
    private String pictureName;

    @Column(name = "picture_path", nullable = false, unique = true)
    private String path;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotePicture that = (NotePicture) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(pictureName, that.pictureName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pictureName);
    }
}
