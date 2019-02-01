package ru.eremin.noteboard.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.eremin.noteboard.dto.BoardDTO;
import ru.eremin.noteboard.entity.Board;
import ru.eremin.noteboard.entity.User;
import ru.eremin.noteboard.repository.BoardRepository;
import ru.eremin.noteboard.repository.CategoryRepository;
import ru.eremin.noteboard.repository.NoteRepository;
import ru.eremin.noteboard.repository.UserRepository;
import ru.eremin.noteboard.service.api.IBoardService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @autor Artem Eremin on 16.12.2018.
 */

@Service(BoardService.NAME)
public class BoardService implements IBoardService {

    public static final String NAME = "boardService";

    @Autowired
    private BoardRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    @Nullable
    @Transactional(readOnly = true)
    public List<BoardDTO> findAll() {
        final List<Board> boardList = repository.findAll();
        if (boardList == null || boardList.isEmpty()) return null;
        return boardList.stream().map(BoardDTO::new).collect(Collectors.toList());
    }

    @Override
    @Nullable
    @Transactional(readOnly = true)
    public List<BoardDTO> findAll(final int page, final int size) {
        final List<Board> boardList = repository.findAll(PageRequest.of(page, size)).getContent();
        if (boardList == null || boardList.isEmpty()) return null;
        return boardList.stream().map(BoardDTO::new).collect(Collectors.toList());
    }

    @Override
    @Nullable
    @Transactional(readOnly = true)
    public BoardDTO findById(@Nullable final String id) {
        if (id == null || id.isEmpty()) return null;
        final Board board = repository.findBoardById(id);
        if (board == null) return null;
        return new BoardDTO(board);
    }

    @Override
    @Nullable
    @Transactional(readOnly = true)
    public List<BoardDTO> findByAuthorId(final String authorId) {
        if (authorId == null || authorId.isEmpty()) return null;
        final List<Board> boardList = repository.findBoardsByAuthorId(authorId);
        if (boardList == null || boardList.isEmpty()) return null;
        return boardList.stream().map(BoardDTO::new).collect(Collectors.toList());
    }

    @Override
    @Nullable
    @Transactional(readOnly = true)
    public List<BoardDTO> findByAuthor(final User author) {
        if (author == null) return null;
        final List<Board> boardList = repository.findBoardsByAuthor(author);
        if (boardList == null || boardList.isEmpty()) return null;
        return boardList.stream().map(BoardDTO::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void insert(@Nullable final BoardDTO boardDTO) {
        if (boardDTO == null) return;
        final Board board = getEntity(boardDTO);
        if (repository.findBoardById(board.getId()) != null) return;
        repository.save(board);
    }

    @Override
    @Transactional
    public void merge(@Nullable final BoardDTO boardDTO) {
        if (boardDTO == null) return;
        final Board board = getEntity(boardDTO);
        repository.save(board);
    }

    @Override
    @Transactional
    public void deleteById(@Nullable final String id) {
        if (id != null && !id.isEmpty()) repository.deleteById(id);
    }

    @Override
    @Transactional
    public void delete(@Nullable final BoardDTO boardDTO) {
        if (boardDTO == null) return;
        final Board board = getEntity(boardDTO);
        repository.delete(board);
    }

    @Override
    @Transactional
    public void update(@Nullable final BoardDTO boardDTO) {
        if (boardDTO == null) return;
        if (boardDTO.getId() == null || boardDTO.getId().isEmpty()) return;
        final Board board = repository.findBoardById(boardDTO.getId());
        if (board == null) return;
        board.setName(boardDTO.getName());
        repository.save(board);
    }

    @NotNull
    @Override
    public Board getEntity(@NotNull final BoardDTO boardDTO) {
        final Board board = new Board();
        board.setId(boardDTO.getId());
        board.setName(boardDTO.getName());
        board.setDate(boardDTO.getDate());
        final User author = userRepository.findUserById(boardDTO.getAuthorId());
        if (author != null) board.setAuthor(author);
        board.setNotes(noteRepository.findNotesByBoard(board));
        board.setCategories(categoryRepository.findCategoriesByBoard(board));
        return board;
    }
}
