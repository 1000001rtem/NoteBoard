package ru.eremin.noteboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @autor Artem Eremin on 25.12.2018.
 */

@Controller
public class IndexController {

    @GetMapping(value = "/")
    public String index() {
        return "index";
    }
}
