package ru.otus.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.project.model.entity.ErrorCommentTagEntity;

public interface ErrorCommentTagEntityRepository extends JpaRepository<ErrorCommentTagEntity, Long> {
}
