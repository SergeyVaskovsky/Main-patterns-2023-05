package ru.otus.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private Long id;

    private String description;

    public ErrorCommentEntity(long id, String description) {
        this.id = id;
        this.description = description;
    }
}
