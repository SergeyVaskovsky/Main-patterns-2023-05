package ru.otus.project.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.project.model.CreateNewErrorCommentDto;
import ru.otus.project.model.ErrorCommentEntity;
import ru.otus.project.model.ErrorCommentTagEntity;
import ru.otus.project.model.TagEntity;
import ru.otus.project.repository.ErrorCommentEntityRepository;
import ru.otus.project.repository.ErrorCommentTagEntityRepository;
import ru.otus.project.repository.TagEntityRepository;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ErrorServiceImpl implements ErrorService {

    private final ErrorCommentEntityRepository errorCommentEntityRepository;
    private final ErrorCommentTagEntityRepository errorCommentTagEntityRepository;
    private final TagEntityRepository tagEntityRepository;

    @Override
    @Transactional
    public void saveErrorAndTags(CreateNewErrorCommentDto dto) {
        var errorCommentEntity = errorCommentEntityRepository.saveAndFlush(new ErrorCommentEntity(null, dto.getDescription()));
        var tagEntityList = tagEntityRepository.saveAllAndFlush(
                dto
                        .getTags()
                        .stream()
                        .map(it -> new TagEntity(it.getId(), it.getName()))
                        .collect(Collectors.toList()));
        errorCommentTagEntityRepository.saveAll(
                tagEntityList.stream().map(it -> new ErrorCommentTagEntity(
                        null,
                        errorCommentEntity,
                        it)).collect(Collectors.toList())
        );
    }
}
