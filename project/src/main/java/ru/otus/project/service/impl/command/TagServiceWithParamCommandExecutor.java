package ru.otus.project.service.impl.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.project.model.Tag;
import ru.otus.project.service.CommandExecutor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceWithParamCommandExecutor implements CommandExecutor<GetListTagsWithParamCommand, TagListCommandResult> {

    @Override
    public TagListCommandResult execute(GetListTagsWithParamCommand command) {
        List<Tag> listTag = command.getFunction().apply(command.getProjectId());
        return new TagListCommandResult(listTag);
    }

}
