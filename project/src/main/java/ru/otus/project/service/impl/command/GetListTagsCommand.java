package ru.otus.project.service.impl.command;

import lombok.Value;
import ru.otus.project.model.Tag;
import ru.otus.project.repository.TagEntityRepository;
import ru.otus.project.service.Command;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Value
public class GetListTagsCommand implements Command {

    TagEntityRepository repository;
    Function<Void, List<Tag>> function;

    public GetListTagsCommand(TagEntityRepository repository) {
        this.repository = repository;
        this.function = (Void v) -> repository
                .findAll()
                .stream()
                .map(it -> Tag.create(it.getId(), it.getName()))
                .collect(Collectors.toList());
    }

}
