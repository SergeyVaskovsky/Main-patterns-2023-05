package ru.otus.project.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.project.model.Tag;
import ru.otus.project.model.TagEntity;
import ru.otus.project.repository.TagEntityRepository;

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
                .map(it -> Tag.create(it.getId(), it.getName(), it.getDescription(), it.getQuantity()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void saveTags(List<Tag> tags) {
        var entities = tags
                .stream()
                .map(it -> new TagEntity(
                        it.getId(),
                        it.getName(),
                        it.getDescription(),
                        it.getQuantity() + 1))
                .collect(Collectors.toList());
        repository.saveAll(entities);

    }
}
