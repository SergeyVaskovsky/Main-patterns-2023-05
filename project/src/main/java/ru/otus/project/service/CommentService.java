package ru.otus.project.service;

import ru.otus.project.model.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> findAll();
}
