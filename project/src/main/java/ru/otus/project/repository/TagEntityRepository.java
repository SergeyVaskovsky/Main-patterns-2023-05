package ru.otus.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.project.model.entity.TagEntity;

public interface TagEntityRepository extends JpaRepository<TagEntity, Long> {

}
