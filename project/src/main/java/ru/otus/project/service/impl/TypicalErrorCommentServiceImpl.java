package ru.otus.project.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.project.model.CreateNewTypicalErrorDto;
import ru.otus.project.model.TypicalErrorComment;
import ru.otus.project.model.entity.TypicalErrorCommentEntity;
import ru.otus.project.repository.TypicalErrorCommentEntityRepository;
import ru.otus.project.service.TypicalErrorCommentService;

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

    @Transactional
    public TypicalErrorComment createNewTypicalError(CreateNewTypicalErrorDto dto) {
        var entity = new TypicalErrorCommentEntity(null, dto.getTitle(), dto.getDescription());
        var entityAfterSave = repository.save(entity);
        return TypicalErrorComment.create(entityAfterSave.getId(), entityAfterSave.getTitle(), entityAfterSave.getDescription());
    }
}
