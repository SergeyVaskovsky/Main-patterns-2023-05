package ru.otus.project.service;

import ru.otus.project.model.Tag;

import java.util.List;

public interface TagService {
    List<Tag> findListTag();

    List<Tag> findListTag(Long projectId);

    List<String> findDescription(long tagId);

}
