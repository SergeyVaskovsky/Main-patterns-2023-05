package ru.otus.project.model;

import lombok.Value;

@Value(staticConstructor = "create")
public class Tag {
    long id;
    String name;
    String description;
    int quantity;
}
