package ru.otus.project.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "error_comment")
@NoArgsConstructor
@AllArgsConstructor
public class ErrorCommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    public ErrorCommentEntity(long id, String description) {
        this.id = id;
        this.description = description;
    }
}