package ru.otus.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "tag")
@NoArgsConstructor
@AllArgsConstructor
public class TagEntity {

    @Id
    private Long id;

    private String name;

    public TagEntity(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
