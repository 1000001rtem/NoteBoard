package ru.eremin.noteboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.eremin.noteboard.dto.BoardDTO;
import ru.eremin.noteboard.dto.CategoryDTO;
import ru.eremin.noteboard.dto.NoteDTO;
import ru.eremin.noteboard.service.api.IBoardService;
import ru.eremin.noteboard.service.api.ICategoryService;
import ru.eremin.noteboard.service.api.INoteService;

import java.util.List;
import java.util.UUID;

/**
 * @autor Artem Eremin on 08.01.2019.
 */

@Controller
public class NoteController {

    @Autowired
    private INoteService noteService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IBoardService boardService;

    @RequestMapping(value = "note/{boardId}", method = RequestMethod.GET)
    public String noteView(final Model model, @PathVariable("boardId") final String boardId) {
        final List<NoteDTO> noteList = noteService.findNotesByBoardId(boardId);
        model.addAttribute("notes", noteList);
        final List<CategoryDTO> categoriesList = categoryService.findCategoriesByBoardId(boardId);
        model.addAttribute("categories", categoriesList);
        final BoardDTO boardDTO = boardService.findById(boardId);
        model.addAttribute("board", boardDTO);
        return "note-view";
    }

    @RequestMapping(value = "note/create-category/{boardId}", method = RequestMethod.GET)
    public String createCategory(final Model model, @PathVariable("boardId") final String boardId) {
        final CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(UUID.randomUUID().toString());
        categoryDTO.setBoardId(boardId);
        categoryDTO.setName("New Category");
        categoryService.insert(categoryDTO);
        final BoardDTO boardDTO = boardService.findById(boardId);
        model.addAttribute("board", boardDTO);
        return "redirect:/note/" + boardId;
    }

    @RequestMapping(value = "note/noteByCategory/boardId={boardId}&categoryId={categoryId}", method = RequestMethod.GET)
    public String getNotesByCategory(final Model model,
                                     @PathVariable("boardId") final String boardId,
                                     @PathVariable("categoryId") final String categoryId){
        final List<NoteDTO> noteList = noteService.findNotesByCategoryId(categoryId);
        model.addAttribute("notes", noteList);
        final List<CategoryDTO> categoriesList = categoryService.findCategoriesByBoardId(boardId);
        model.addAttribute("categories", categoriesList);
        final BoardDTO boardDTO = boardService.findById(boardId);
        model.addAttribute("board", boardDTO);
        return "note-view";
    }
}
