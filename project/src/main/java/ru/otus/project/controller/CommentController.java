package ru.otus.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.project.model.CreateNewErrorCommentDto;
import ru.otus.project.model.CreateNewTypicalErrorDto;
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
        return tagService.findListTag();
    }

    @GetMapping("/error-tag/{projectId}")
    public List<Tag> getErrorTags(@PathVariable Long projectId) {
        return tagService.findListTag(projectId);
    }

    @PostMapping("/error-tag")
    public void saveErrorAndTags(@RequestBody CreateNewErrorCommentDto dto) {
        errorService.saveErrorAndTags(dto);
    }

    @GetMapping("error-description/{tagId}")
    public List<String> getErrorDescription(@PathVariable Long tagId) {
        return tagService.findDescription(tagId);
    }

    @PostMapping("/add-new-typical-error")
    public TypicalErrorComment addNewTypicalError(@RequestBody CreateNewTypicalErrorDto dto) {
        return typicalErrorCommentService.createNewTypicalError(dto);
    }
}
