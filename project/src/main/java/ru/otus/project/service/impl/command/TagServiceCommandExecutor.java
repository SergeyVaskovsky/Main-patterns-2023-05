package ru.otus.project.service.impl.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.project.model.Tag;
import ru.otus.project.service.CommandExecutor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceCommandExecutor implements CommandExecutor<GetListTagsCommand, TagListCommandResult> {

    @Override
    public TagListCommandResult execute(GetListTagsCommand command) {
        List<Tag> listTag = command.getFunction().apply(null);
        return new TagListCommandResult(listTag);
    }

}
