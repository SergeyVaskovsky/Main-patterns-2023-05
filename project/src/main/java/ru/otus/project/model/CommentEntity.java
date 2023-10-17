package ru.otus.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "comment")
@NoArgsConstructor
@AllArgsConstructor
public class CommentEntity {

    @Id
    private Long id;

    private String title;

    private String description;

}
