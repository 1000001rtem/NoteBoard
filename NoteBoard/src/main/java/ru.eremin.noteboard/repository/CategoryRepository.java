package ru.eremin.noteboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.eremin.noteboard.entity.Board;
import ru.eremin.noteboard.entity.Category;

import java.util.List;

/**
 * @autor Artem Eremin on 16.12.2018.
 */

@Repository(CategoryRepository.NAME)
public interface CategoryRepository extends JpaRepository<Category, String> {

    String NAME = "categoryRepository";

    Category findCategoryById(String id);

    List<Category> findCategoriesByBoard(Board board);

    List<Category> findCategoriesByBoardId(String boardId);

}
