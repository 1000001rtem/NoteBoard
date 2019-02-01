package ru.eremin.noteboard.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.eremin.noteboard.dto.BoardDTO;
import ru.eremin.noteboard.dto.CategoryDTO;
import ru.eremin.noteboard.entity.Board;
import ru.eremin.noteboard.entity.Category;
import ru.eremin.noteboard.repository.BoardRepository;
import ru.eremin.noteboard.repository.CategoryRepository;
import ru.eremin.noteboard.service.api.ICategoryService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @autor Artem Eremin on 19.12.2018.
 */

@Service(CategoryService.NAME)
public class CategoryService implements ICategoryService {

    public static final String NAME = "categoryService";

    @Autowired
    private CategoryRepository repository;

    @Autowired
    private BoardRepository boardRepository;

    @Override
    @Nullable
    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll() {
        final List<Category> categoryList = repository.findAll();
        if (categoryList == null || categoryList.isEmpty()) return null;
        return categoryList.stream().map(CategoryDTO::new).collect(Collectors.toList());
    }

    @Override
    @Nullable
    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll(final int page, final int size) {
        final List<Category> categoryList = repository.findAll(PageRequest.of(page, size)).getContent();
        if (categoryList == null || categoryList.isEmpty()) return null;
        return categoryList.stream().map(CategoryDTO::new).collect(Collectors.toList());
    }

    @Override
    @Nullable
    @Transactional(readOnly = true)
    public CategoryDTO findById(@Nullable final String id) {
        if (id == null || id.isEmpty()) return null;
        final Category category = repository.findCategoryById(id);
        if (category == null) return null;
        return new CategoryDTO(category);
    }

    @Override
    @Nullable
    @Transactional(readOnly = true)
    public List<CategoryDTO> findCategoriesByBoard(@Nullable final BoardDTO boardDTO) {
        if (boardDTO == null) return null;
        if (boardDTO.getId() == null || boardDTO.getId().isEmpty()) return null;
        final List<Category> categoryList = repository.findCategoriesByBoardId(boardDTO.getId());
        if (categoryList == null || categoryList.isEmpty()) return null;
        return categoryList.stream().map(CategoryDTO::new).collect(Collectors.toList());
    }

    @Override
    @Nullable
    @Transactional(readOnly = true)
    public List<CategoryDTO> findCategoriesByBoardId(final String boardId) {
        if(boardId == null || boardId.isEmpty()) return null;
        final List<Category> categoryList = repository.findCategoriesByBoardId(boardId);
        return categoryList.stream().map(CategoryDTO::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void insert(@Nullable final CategoryDTO categoryDTO) {
        if (categoryDTO == null) return;
        final Category category = getEntity(categoryDTO);
        if (repository.findCategoryById(category.getId()) != null) return;
        repository.save(category);
    }

    @Override
    @Transactional
    public void merge(@Nullable final CategoryDTO categoryDTO) {
        if (categoryDTO == null) return;
        final Category category = getEntity(categoryDTO);
        repository.save(category);
    }

    @Override
    @Transactional
    public void deleteById(@Nullable final String id) {
        if (id != null && !id.isEmpty()) repository.deleteById(id);
    }

    @Override
    @Transactional
    public void delete(@Nullable final CategoryDTO categoryDTO) {
        if (categoryDTO == null) return;
        final Category category = getEntity(categoryDTO);
        repository.delete(category);
    }

    @Override
    @Transactional
    public void update(@Nullable final CategoryDTO categoryDTO) {
        if (categoryDTO == null) return;
        if (categoryDTO.getId() == null || categoryDTO.getId().isEmpty()) return;
        final Category category = repository.findCategoryById(categoryDTO.getId());
        if (category == null) return;
        category.setName(categoryDTO.getName());
        repository.save(category);
    }

    @NotNull
    @Override
    public Category getEntity(@NotNull final CategoryDTO categoryDTO) {
        final Category category = new Category();
        final Board board = boardRepository.findBoardById(categoryDTO.getBoardId());
        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        if (board != null) category.setBoard(board);
        return category;
    }
}
