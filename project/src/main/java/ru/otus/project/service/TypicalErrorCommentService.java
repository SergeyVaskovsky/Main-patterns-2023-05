package ru.otus.project.service;

import ru.otus.project.model.CreateNewTypicalErrorDto;
import ru.otus.project.model.TypicalErrorComment;

import java.util.List;

public interface TypicalErrorCommentService {
    List<TypicalErrorComment> findAll();

    TypicalErrorComment createNewTypicalError(CreateNewTypicalErrorDto dto);
}
