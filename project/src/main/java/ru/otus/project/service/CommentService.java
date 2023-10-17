package ru.otus.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.project.model.Comment;
import ru.otus.project.repository.CommentEntityRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentEntityRepository repository;

    public List<Comment> findAll() {
        return repository
                .findAll()
                .stream()
                .map(it -> Comment.create(it.getId(), it.getTitle(), it.getDescription()))
                .collect(Collectors.toList());
    }
}
