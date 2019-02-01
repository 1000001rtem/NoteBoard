package ru.eremin.noteboard.service;

import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.eremin.noteboard.dto.*;
import ru.eremin.noteboard.entity.*;
import ru.eremin.noteboard.repository.*;
import ru.eremin.noteboard.service.api.INoteService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @autor Artem Eremin on 20.12.2018.
 */

@Service(NoteService.NAME)
public class NoteService implements INoteService {

    public static final String NAME = "noteService";

    @Autowired
    private NoteRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private NotePictureRepository notePictureRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    @Nullable
    @Transactional(readOnly = true)
    public List<NoteDTO> findNotesByAuthor(@Nullable final UserDTO author) {
        if (author == null) return null;
        final User user = userRepository.findUserById(author.getId());
        if (user == null) return null;
        final List<Note> noteList = repository.findNotesByAuthor(user);
        if (noteList == null || noteList.isEmpty()) return null;
        return noteList.stream().map(NoteDTO::new).collect(Collectors.toList());
    }

    @Override
    @Nullable
    @Transactional(readOnly = true)
    public List<NoteDTO> findNotesByCategory(@Nullable final CategoryDTO categoryDTO) {
        if (categoryDTO == null) return null;
        final Category category = categoryRepository.findCategoryById(categoryDTO.getId());
        if (category == null) return null;
        final List<Note> noteList = repository.findNotesByCategory(category);
        if (noteList == null || noteList.isEmpty()) return null;
        return noteList.stream().map(NoteDTO::new).collect(Collectors.toList());
    }

    @Override
    @Nullable
    @Transactional(readOnly = true)
    public List<NoteDTO> findNotesByCategoryId(@Nullable final String categoryId) {
        if(categoryId == null || categoryId.isEmpty()) return null;
        final List<Note> noteList = repository.findNotesByCategoryId(categoryId);
        if(noteList == null || noteList.isEmpty()) return null;
        return noteList.stream().map(NoteDTO::new).collect(Collectors.toList());
    }

    @Override
    @Nullable
    @Transactional(readOnly = true)
    public List<NoteDTO> findNotesByBoard(@Nullable final BoardDTO boardDTO) {
        if (boardDTO == null) return null;
        final Board board = boardRepository.findBoardById(boardDTO.getId());
        if (board == null) return null;
        final List<Note> noteList = repository.findNotesByBoard(board);
        if (noteList == null || noteList.isEmpty()) return null;
        return noteList.stream().map(NoteDTO::new).collect(Collectors.toList());
    }

    @Override
    @Nullable
    @Transactional(readOnly = true)
    public List<NoteDTO> findNotesByBoardId(@Nullable final String id) {
        if (id == null || id.isEmpty()) return null;
        final List<Note> noteList = repository.findNotesByBoardId(id);
        if (noteList == null || noteList.isEmpty()) return null;
        return noteList.stream().map(NoteDTO::new).collect(Collectors.toList());
    }

    @Override
    @Nullable
    @Transactional(readOnly = true)
    public List<NoteDTO> findAll() {
        final List<Note> noteList = repository.findAll();
        if (noteList == null || noteList.isEmpty()) return null;
        return noteList.stream().map(NoteDTO::new).collect(Collectors.toList());
    }

    @Override
    @Nullable
    @Transactional(readOnly = true)
    public List<NoteDTO> findAll(final int page, final int size) {
        final List<Note> noteList = repository.findAll(PageRequest.of(page, size)).getContent();
        if (noteList == null || noteList.isEmpty()) return null;
        return noteList.stream().map(NoteDTO::new).collect(Collectors.toList());
    }

    @Override
    @Nullable
    @Transactional(readOnly = true)
    public NoteDTO findById(@Nullable final String id) {
        if (id == null || id.isEmpty()) return null;
        final Note note = repository.findNoteById(id);
        if (note == null) return null;
        return new NoteDTO(note);
    }

    @Override
    @Transactional
    public void insert(@Nullable final NoteDTO noteDTO) {
        if (noteDTO == null) return;
        final Note note = getEntity(noteDTO);
        if (repository.findNoteById(noteDTO.getId()) != null) return;
        repository.save(note);
    }

    @Override
    @Transactional
    public void merge(@Nullable final NoteDTO noteDTO) {
        if (noteDTO == null) return;
        final Note note = getEntity(noteDTO);
        note.setCategory(categoryRepository.findCategoryById(noteDTO.getCategoryId()));
        note.setStatus(noteDTO.getStatus());
        note.setType(noteDTO.getType());
        repository.save(note);
    }

    @Override
    @Transactional
    public void deleteById(@Nullable final String id) {
        if (id != null && !id.isEmpty()) repository.deleteById(id);
    }

    @Override
    @Transactional
    public void delete(@Nullable final NoteDTO noteDTO) {
        if (noteDTO == null) return;
        final Note note = getEntity(noteDTO);
        repository.delete(note);
    }

    @Override
    @Transactional
    public void update(@Nullable final NoteDTO noteDTO) {
        if (noteDTO == null) return;
        if (noteDTO.getId() == null || noteDTO.getId().isEmpty()) return;
        final Note note = repository.findNoteById(noteDTO.getId());
        note.setCategory(categoryRepository.findCategoryById(noteDTO.getCategoryId()));
        note.setStatus(noteDTO.getStatus());
        note.setType(noteDTO.getType());
        repository.save(note);
    }

    @Override
    public Note getEntity(final NoteDTO noteDTO) {
        final Note note = new Note();
        note.setId(noteDTO.getId());
        note.setAuthor(userRepository.findUserById(noteDTO.getAuthorId()));
        note.setData(noteDTO.getData());
        note.setCategory(categoryRepository.findCategoryById(noteDTO.getCategoryId()));
        note.setBoard(boardRepository.findBoardById(noteDTO.getBoardId()));
        note.setType(noteDTO.getType());
        note.setPicture(notePictureRepository.findNotePictureById(noteDTO.getPictureId()));
        note.setDeadline(noteDTO.getNoteDeadline());
        note.setStatus(noteDTO.getStatus());
        note.setComments(commentRepository.findCommentsByNote(note));
        return note;
    }
}
