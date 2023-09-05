package ru.otus.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {


    @GetMapping("/doMethod")
    public Object doMethod() {
        return null;
    }

}

