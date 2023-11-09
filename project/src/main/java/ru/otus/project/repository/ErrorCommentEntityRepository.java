package ru.otus.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.otus.project.model.entity.ErrorCommentEntity;

import java.util.List;

public interface ErrorCommentEntityRepository extends JpaRepository<ErrorCommentEntity, Long> {

    @Query("select e.description from ErrorCommentEntity e " +
            "join ErrorCommentTagEntity et on e.id = et.errorComment.id " +
            "where et.tag.id = :tagId ")
    List<String> findErrorsByTagId(Long tagId);
}
