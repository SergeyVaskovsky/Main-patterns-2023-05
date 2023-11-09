package ru.otus.project.service.impl.command;

import lombok.Value;
import ru.otus.project.model.Tag;
import ru.otus.project.service.CommandResult;

import java.util.List;

@Value
public class TagListCommandResult implements CommandResult {
    List<Tag> listTag;

    public TagListCommandResult(List<Tag> param) {
        this.listTag = param;
    }
}
