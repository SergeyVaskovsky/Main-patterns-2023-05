package ru.otus.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.project.model.Comment;
import ru.otus.project.model.Tag;
import ru.otus.project.service.CommentService;
import ru.otus.project.service.TagService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final TagService tagService;

    @GetMapping("/typical-error")
    public List<Comment> getComments() {
        return commentService.findAll();
    }

    @GetMapping("/error-tag")
    public List<Tag> getErrorTags() {
        return tagService.findAll();
    }

    @PostMapping("/error-tag")
    public void saveTags(List<Tag> tags) {
        tagService.saveTags(tags);
    }
}
