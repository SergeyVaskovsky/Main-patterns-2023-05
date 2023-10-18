package ru.otus.project.model;

import lombok.Value;

@Value(staticConstructor = "create")
public class ErrorComment {
    long id;
    String description;
}