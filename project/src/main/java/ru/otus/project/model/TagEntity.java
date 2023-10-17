package ru.otus.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
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

    private String description;

    private Integer quantity;

    @Version
    private int version;

    public TagEntity(long id, String name, String description, int quantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
    }
}
