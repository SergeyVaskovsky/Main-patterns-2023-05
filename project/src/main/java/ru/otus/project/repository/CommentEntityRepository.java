package ru.otus.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.project.model.CommentEntity;

public interface CommentEntityRepository extends JpaRepository<CommentEntity, Long> {

}
