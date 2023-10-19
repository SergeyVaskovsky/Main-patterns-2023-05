package ru.otus.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.project.model.entity.ErrorCommentEntity;

public interface ErrorCommentEntityRepository extends JpaRepository<ErrorCommentEntity, Long> {
}
