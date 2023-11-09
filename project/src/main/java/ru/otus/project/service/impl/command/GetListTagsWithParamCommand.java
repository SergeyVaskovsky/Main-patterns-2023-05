package ru.otus.project.service.impl.command;

import lombok.Getter;
import lombok.Setter;
import ru.otus.project.model.Tag;
import ru.otus.project.repository.TagEntityRepository;
import ru.otus.project.service.Command;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


@Getter
public class GetListTagsWithParamCommand implements Command {

    private final TagEntityRepository repository;
    private final Function<Long, List<Tag>> function;
    @Setter
    Long projectId = null;

    public GetListTagsWithParamCommand(TagEntityRepository repository) {
        this.repository = repository;
        this.function = (Long projectId) -> repository
                .findAllByProjectId(projectId)
                .stream()
                .map(it -> Tag.create(it.getId(), it.getName()))
                .collect(Collectors.toList());
    }


}
