package ru.eremin.noteboard.service.api;

import ru.eremin.noteboard.dto.BoardDTO;
import ru.eremin.noteboard.dto.CategoryDTO;
import ru.eremin.noteboard.entity.Category;

import java.util.List;

/**
 * @autor Artem Eremin on 16.12.2018.
 */

public interface ICategoryService extends IService<CategoryDTO, Category> {

    List<CategoryDTO> findCategoriesByBoard(final BoardDTO boardDTO);

    List<CategoryDTO> findCategoriesByBoardId(final String boardId);
}
