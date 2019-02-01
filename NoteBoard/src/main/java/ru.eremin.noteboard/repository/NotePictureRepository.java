package ru.eremin.noteboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.eremin.noteboard.entity.NotePicture;

/**
 * @autor Artem Eremin on 16.12.2018.
 */

@Repository(NotePictureRepository.NAME)
public interface NotePictureRepository extends JpaRepository<NotePicture, String> {

    String NAME = "notePictureRepository";

    NotePicture findNotePictureById(String id);

    NotePicture findNotePictureByPath(String path);

    NotePicture findNotePictureByPictureName(String name);

}
