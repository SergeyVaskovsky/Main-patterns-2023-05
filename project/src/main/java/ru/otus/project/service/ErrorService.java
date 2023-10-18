package ru.otus.project.service;

import ru.otus.project.model.CreateNewErrorCommentDto;

public interface ErrorService {

    void saveErrorAndTags(CreateNewErrorCommentDto dto);
}
