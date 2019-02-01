package ru.eremin.noteboard.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.eremin.noteboard.dto.NoteDTO;
import ru.eremin.noteboard.dto.ResultDTO;
import ru.eremin.noteboard.service.api.INoteService;

import java.util.List;

/**
 * @autor Artem Eremin on 23.12.2018.
 */

@RestController
@RequestMapping("api/noteREST")
public class NoteREST {

    public static final String JSON = "application/json";

    @Autowired
    private INoteService noteService;

    @GetMapping(value = "getById", produces = JSON)
    public NoteDTO getById(@RequestParam(value = "id") final String id) {
        if (id == null || id.isEmpty()) return null;
        final NoteDTO noteDTO = noteService.findById(id);
        if (noteDTO != null) return noteDTO;
        return null;
    }

    @GetMapping(value = "getAll", produces = JSON)
    public List<NoteDTO> getAll() {
        final List<NoteDTO> noteDTOList = noteService.findAll();
        if (noteDTOList == null || noteDTOList.isEmpty()) return null;
        return noteDTOList;
    }

    @GetMapping(value = "getAllLimit", produces = JSON)
    public List<NoteDTO> getAll(@RequestParam(value = "page") final int page, @RequestParam(value = "size") final int size) {
        final List<NoteDTO> noteDTOList = noteService.findAll(page, size);
        if (noteDTOList == null || noteDTOList.isEmpty()) return null;
        return noteDTOList;
    }

    @GetMapping(value = "merge", produces = JSON, consumes = JSON)
    public ResultDTO merge(final NoteDTO noteDTO) {
        if (noteDTO == null) return new ResultDTO();
        noteService.merge(noteDTO);
        if (noteService.findById(noteDTO.getId()) != null) return new ResultDTO(true);
        return new ResultDTO();
    }

    @GetMapping(value = "delete", produces = JSON)
    public ResultDTO delete(@RequestParam(value = "id") final String id) {
        if (id == null || id.isEmpty()) return new ResultDTO();
        noteService.deleteById(id);
        if (noteService.findById(id) == null) return new ResultDTO(true);
        return new ResultDTO();
    }
}
