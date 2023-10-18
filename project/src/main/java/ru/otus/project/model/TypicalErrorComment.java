package ru.otus.project.model;

import lombok.Value;

@Value(staticConstructor = "create")
public class TypicalErrorComment {
    long id;
    String title;
    String description;
}
