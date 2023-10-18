package ru.otus.project.model;

import lombok.Value;

import java.util.List;

@Value(staticConstructor = "create")
public class CreateNewErrorCommentDto {
    List<Tag> tags;
    String description;
}
