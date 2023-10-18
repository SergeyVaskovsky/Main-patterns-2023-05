package ru.otus.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.project.model.CreateNewErrorCommentDto;
import ru.otus.project.model.Tag;
import ru.otus.project.model.TypicalErrorComment;
import ru.otus.project.service.ErrorService;
import ru.otus.project.service.TagService;
import ru.otus.project.service.TypicalErrorCommentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final TypicalErrorCommentService typicalErrorCommentService;
    private final TagService tagService;
    private final ErrorService errorService;

    @GetMapping("/typical-error")
    public List<TypicalErrorComment> getComments() {
        return typicalErrorCommentService.findAll();
    }

    @GetMapping("/error-tag")
    public List<Tag> getErrorTags() {
        return tagService.findAll();
    }

    @PostMapping("/error-tag")
    public void saveTags(CreateNewErrorCommentDto dto) {
        errorService.saveErrorAndTags(dto);
    }
}
