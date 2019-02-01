package ru.eremin.noteboard.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.eremin.noteboard.dto.BoardDTO;
import ru.eremin.noteboard.entity.User;
import ru.eremin.noteboard.service.api.IBoardService;
import ru.eremin.noteboard.service.security.AuthService;

import java.security.Principal;
import java.util.*;

/**
 * @autor Artem Eremin on 04.01.2019.
 */

@Controller
public class BoardsController {

    @Autowired
    private IBoardService service;

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/boards", method = RequestMethod.GET)
    public String boards(final Model model, @Nullable final Principal principal) {
        if(principal == null) return "boards-view";
        final User user = authService.getUser(principal);
        if(user == null) return "boards-view";
        final List<BoardDTO> boardDTOList = service.findByAuthor(user);
        model.addAttribute("boards", boardDTOList);
        return "boards-view";
    }

    @RequestMapping(value = "/create-board", method = RequestMethod.GET)
    public String create(@Nullable final Principal principal){
        if(principal==null) return "redirect:/logout";
        final BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(UUID.randomUUID().toString());
        boardDTO.setName("New Board");
        boardDTO.setDate(new Calendar.Builder().setInstant(new Date()).build());
        final User user = authService.getUser(principal);
        if(user==null) return "redirect:/logout";
        boardDTO.setAuthorId(user.getId());
        service.insert(boardDTO);
        return "redirect:/boards";
    }

    @RequestMapping (value = "/delete-board/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") final String id){
        service.deleteById(id);
        return "redirect:/boards";
    }

    @RequestMapping(value = "/edit-board/{id}", method = RequestMethod.GET)
    public String edit(final ModelMap model, @PathVariable("id") final String id){
        final Optional<BoardDTO> optional = Optional.of(service.findById(id));
        final BoardDTO boardDTO = optional.orElse(new BoardDTO());
        model.addAttribute("board", boardDTO);
        return "board-edit-view";
    }

    @RequestMapping (value = "/board-save", method = RequestMethod.POST)
    public String save(final BoardDTO boardDTO, final BindingResult result){
        if(!result.hasErrors()) service.update(boardDTO);
        return "redirect:/boards";
    }
}
