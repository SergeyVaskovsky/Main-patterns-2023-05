package ru.otus.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.project.model.Tag;
import ru.otus.project.repository.TagEntityRepository;
import ru.otus.project.service.TagService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagEntityRepository repository;

    public List<Tag> findAll() {
        return repository
                .findAll()
                .stream()
                .map(it -> Tag.create(it.getId(), it.getName()))
                .collect(Collectors.toList());
    }

}
