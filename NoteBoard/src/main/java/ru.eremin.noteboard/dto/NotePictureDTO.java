package ru.eremin.noteboard.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.eremin.noteboard.entity.NotePicture;

import java.io.Serializable;
import java.util.Objects;

/**
 * @autor Artem Eremin on 16.12.2018.
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
public class NotePictureDTO extends AbstractDTO implements Serializable {

    private String id;
    private String pictureName;
    private String path;

    public NotePictureDTO(final NotePicture notePicture) {
        if (notePicture == null) return;
        this.id = notePicture.getId();
        this.pictureName = notePicture.getPictureName();
        this.path = notePicture.getPath();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotePictureDTO that = (NotePictureDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
