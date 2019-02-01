package ru.eremin.noteboard.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.eremin.noteboard.dto.CommentDTO;
import ru.eremin.noteboard.dto.NoteDTO;
import ru.eremin.noteboard.dto.UserDTO;
import ru.eremin.noteboard.entity.Comment;
import ru.eremin.noteboard.entity.Note;
import ru.eremin.noteboard.entity.User;
import ru.eremin.noteboard.repository.CommentRepository;
import ru.eremin.noteboard.repository.NoteRepository;
import ru.eremin.noteboard.repository.UserRepository;
import ru.eremin.noteboard.service.api.ICommentService;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @autor Artem Eremin on 19.12.2018.
 */

@Service(CommentService.NAME)
public class CommentService implements ICommentService {

    public static final String NAME = "commentService";

    @Autowired
    private CommentRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NoteRepository noteRepository;

    @Override
    @Nullable
    @Transactional(readOnly = true)
    public List<CommentDTO> findAll() {
        final List<Comment> commentList = repository.findAll();
        if (commentList == null || commentList.isEmpty()) return null;
        return commentList.stream().map(CommentDTO::new).collect(Collectors.toList());
    }

    @Override
    @Nullable
    @Transactional(readOnly = true)
    public List<CommentDTO> findAll(final int page, final int size) {
        final List<Comment> commentList = repository.findAll(PageRequest.of(page, size)).getContent();
        if (commentList == null || commentList.isEmpty()) return null;
        return commentList.stream().map(CommentDTO::new).collect(Collectors.toList());
    }

    @Override
    @Nullable
    @Transactional(readOnly = true)
    public CommentDTO findById(@Nullable final String id) {
        if (id == null || id.isEmpty()) return null;
        final Comment comment = repository.findCommentById(id);
        if (comment == null) return null;
        return new CommentDTO(comment);
    }

    @Override
    @Nullable
    @Transactional(readOnly = true)
    public List<CommentDTO> findCommentsByAuthor(@Nullable final UserDTO authorDTO) {
        if (authorDTO == null) return null;
        final User user = userRepository.findUserById(authorDTO.getId());
        if (user == null) return null;
        final List<Comment> commentList = repository.findCommentByAuthor(user);
        if (commentList == null || commentList.isEmpty()) return null;
        return commentList.stream().map(CommentDTO::new).collect(Collectors.toList());
    }

    @Override
    @Nullable
    @Transactional(readOnly = true)
    public List<CommentDTO> findCommentsByNote(@Nullable final NoteDTO noteDTO) {
        if (noteDTO == null) return null;
        final Note note = noteRepository.findNoteById(noteDTO.getId());
        if (note == null) return null;
        final List<Comment> commentList = repository.findCommentsByNote(note);
        if (commentList == null || commentList.isEmpty()) return null;
        return commentList.stream().map(CommentDTO::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void insert(@Nullable final CommentDTO commentDTO) {
        if (commentDTO == null) return;
        final Comment comment = getEntity(commentDTO);
        if (repository.findCommentById(comment.getId()) != null) return;
        repository.save(comment);
    }

    @Override
    @Transactional
    public void merge(@Nullable final CommentDTO commentDTO) {
        if (commentDTO == null) return;
        final Comment comment = getEntity(commentDTO);
        repository.save(comment);
    }

    @Override
    @Transactional
    public void deleteById(@Nullable final String id) {
        if (id != null && !id.isEmpty()) repository.deleteById(id);
    }

    @Override
    @Transactional
    public void delete(@Nullable final CommentDTO commentDTO) {
        if (commentDTO == null) return;
        final Comment comment = getEntity(commentDTO);
        repository.delete(comment);
    }

    @Override
    @Transactional
    public void update(@Nullable final CommentDTO commentDTO) {
        if (commentDTO == null) return;
        if (commentDTO.getId() == null || commentDTO.getId().isEmpty()) return;
        final Comment comment = repository.findCommentById(commentDTO.getId());
        if (comment == null) return;
        comment.setText(commentDTO.getText());
        comment.setDate(new Calendar.Builder().setInstant(new Date()).build());
        repository.save(comment);
    }

    @Override
    @NotNull
    public Comment getEntity(@NotNull final CommentDTO commentDTO) {
        final Comment comment = new Comment();
        comment.setId(commentDTO.getId());
        comment.setText(commentDTO.getText());
        comment.setDate(commentDTO.getDate());
        comment.setAuthor(userRepository.findUserById(commentDTO.getAuthorId()));
        comment.setNote(noteRepository.findNoteById(commentDTO.getNoteId()));
        return comment;
    }
}
