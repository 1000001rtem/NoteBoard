package ru.eremin.noteboard.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.eremin.noteboard.dto.NotePictureDTO;
import ru.eremin.noteboard.entity.NotePicture;
import ru.eremin.noteboard.repository.NotePictureRepository;
import ru.eremin.noteboard.service.api.INotePictureService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @autor Artem Eremin on 19.12.2018.
 */

@Service(NotePictureService.NAME)
public class NotePictureService implements INotePictureService {

    public static final String NAME = "notePictureService";

    @Autowired
    private NotePictureRepository repository;

    @Override
    @Nullable
    @Transactional(readOnly = true)
    public NotePictureDTO findNotePictureByPath(@Nullable final String path) {
        if (path == null || path.isEmpty()) return null;
        final NotePicture notePicture = repository.findNotePictureByPath(path);
        if (notePicture == null) return null;
        return new NotePictureDTO(notePicture);
    }

    @Override
    @Nullable
    @Transactional(readOnly = true)
    public NotePictureDTO findNotePictureByPictureName(@Nullable final String name) {
        if (name == null || name.isEmpty()) return null;
        final NotePicture notePicture = repository.findNotePictureByPictureName(name);
        if (notePicture == null) return null;
        return new NotePictureDTO(notePicture);
    }

    @Override
    @Nullable
    @Transactional(readOnly = true)
    public List<NotePictureDTO> findAll() {
        final List<NotePicture> notePictures = repository.findAll();
        if (notePictures == null || notePictures.isEmpty()) return null;
        return notePictures.stream().map(NotePictureDTO::new).collect(Collectors.toList());
    }

    @Override
    @Nullable
    @Transactional(readOnly = true)
    public List<NotePictureDTO> findAll(final int page, final int size) {
        final List<NotePicture> notePictures = repository.findAll(PageRequest.of(page, size)).getContent();
        if (notePictures == null || notePictures.isEmpty()) return null;
        return notePictures.stream().map(NotePictureDTO::new).collect(Collectors.toList());
    }

    @Override
    @Nullable
    @Transactional(readOnly = true)
    public NotePictureDTO findById(@Nullable final String id) {
        if (id == null || id.isEmpty()) return null;
        final NotePicture notePicture = repository.findNotePictureById(id);
        if (notePicture == null) return null;
        return new NotePictureDTO(notePicture);
    }

    @Override
    @Transactional
    public void insert(@Nullable final NotePictureDTO notePictureDTO) {
        if (notePictureDTO == null) return;
        final NotePicture notePicture = getEntity(notePictureDTO);
        if (repository.findNotePictureById(notePicture.getId()) != null) return;
        repository.save(notePicture);
    }

    @Override
    @Transactional
    public void merge(@Nullable final NotePictureDTO notePictureDTO) {
        if (notePictureDTO == null) return;
        final NotePicture notePicture = getEntity(notePictureDTO);
        repository.save(notePicture);
    }

    @Override
    @Transactional
    public void deleteById(@Nullable final String id) {
        if (id != null && !id.isEmpty()) repository.deleteById(id);
    }

    @Override
    @Transactional
    public void delete(@Nullable final NotePictureDTO notePictureDTO) {
        if (notePictureDTO == null) return;
        final NotePicture notePicture = getEntity(notePictureDTO);
        repository.delete(notePicture);
    }

    @Override
    @Transactional
    public void update(@Nullable final NotePictureDTO notePictureDTO) {
        if (notePictureDTO == null) return;
        if (notePictureDTO.getId() == null || notePictureDTO.getId().isEmpty()) return;
        final NotePicture notePicture = repository.findNotePictureById(notePictureDTO.getId());
        if (notePicture == null) return;
        notePicture.setPath(notePictureDTO.getPath());
        notePicture.setPictureName(notePictureDTO.getPictureName());
        repository.save(notePicture);
    }

    @Override
    @NotNull
    public NotePicture getEntity(@NotNull final NotePictureDTO notePictureDTO) {
        final NotePicture notePicture = new NotePicture();
        notePicture.setId(notePictureDTO.getId());
        notePicture.setPath(notePictureDTO.getPath());
        notePicture.setPictureName(notePictureDTO.getPictureName());
        return notePicture;
    }
}
