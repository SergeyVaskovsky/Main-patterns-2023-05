package ru.otus.project.model;

import lombok.Value;

@Value(staticConstructor = "create")
public class CreateNewTypicalErrorDto {
    String title;
    String description;
}
