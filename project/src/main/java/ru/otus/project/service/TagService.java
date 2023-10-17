package ru.otus.project.service;

import ru.otus.project.model.Tag;

import java.util.List;

public interface TagService {
    List<Tag> findAll();

    void saveTags(List<Tag> tags);
}
