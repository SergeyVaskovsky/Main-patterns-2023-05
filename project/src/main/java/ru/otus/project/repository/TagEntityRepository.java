package ru.otus.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.project.model.Tag;
import ru.otus.project.model.entity.TagEntity;

import java.util.List;

public interface TagEntityRepository extends JpaRepository<TagEntity, Long> {
    List<Tag> findAllByProjectId(Long projectId);
}
