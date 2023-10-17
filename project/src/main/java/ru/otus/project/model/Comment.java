package ru.otus.project.model;

import lombok.Value;

@Value(staticConstructor = "create")
public class Comment {
    long id;
    String title;
    String description;
}
