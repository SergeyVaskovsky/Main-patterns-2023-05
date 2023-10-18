package ru.otus.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.project.model.TypicalErrorComment;
import ru.otus.project.repository.TypicalErrorCommentEntityRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TypicalErrorCommentServiceImpl implements TypicalErrorCommentService {
    private final TypicalErrorCommentEntityRepository repository;

    public List<TypicalErrorComment> findAll() {
        return repository
                .findAll()
                .stream()
                .map(it -> TypicalErrorComment.create(it.getId(), it.getTitle(), it.getDescription()))
                .collect(Collectors.toList());
    }
}
