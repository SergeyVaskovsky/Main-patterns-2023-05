package ru.otus.project.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "error_comment_tag")
@NoArgsConstructor
@AllArgsConstructor
public class ErrorCommentTagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private ErrorCommentEntity errorComment;

    @ManyToOne(fetch = FetchType.LAZY)
    private TagEntity tag;

}
