package ru.eremin.noteboard.service.api;

import ru.eremin.noteboard.dto.NotePictureDTO;
import ru.eremin.noteboard.entity.NotePicture;

/**
 * @autor Artem Eremin on 16.12.2018.
 */

public interface INotePictureService extends IService<NotePictureDTO, NotePicture> {

    NotePictureDTO findNotePictureByPath(final String path);

    NotePictureDTO findNotePictureByPictureName(final String name);
}
