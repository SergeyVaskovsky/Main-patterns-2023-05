package ru.otus.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.project.model.Comment;
import ru.otus.project.service.CommentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/typical-error")
    public List<Comment> getComments() {
        return commentService.findAll();
    }
}
