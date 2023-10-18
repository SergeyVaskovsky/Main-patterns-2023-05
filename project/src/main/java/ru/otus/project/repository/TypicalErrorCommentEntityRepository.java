package ru.otus.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.project.model.TypicalErrorCommentEntity;

public interface TypicalErrorCommentEntityRepository extends JpaRepository<TypicalErrorCommentEntity, Long> {

}
