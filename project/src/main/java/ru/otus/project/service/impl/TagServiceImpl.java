package ru.otus.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.project.model.Tag;
import ru.otus.project.repository.ErrorCommentEntityRepository;
import ru.otus.project.repository.TagEntityRepository;
import ru.otus.project.service.CommandDispatcher;
import ru.otus.project.service.TagService;
import ru.otus.project.service.impl.command.GetListTagsCommand;
import ru.otus.project.service.impl.command.GetListTagsWithParamCommand;
import ru.otus.project.service.impl.command.TagListCommandResult;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final CommandDispatcher commandDispatcher;
    private final TagEntityRepository tagEntityRepository;
    private final ErrorCommentEntityRepository errorCommentEntityRepository;

    @Override
    public List<Tag> findListTag() {
        GetListTagsCommand command = new GetListTagsCommand(tagEntityRepository);
        TagListCommandResult tagListCommandResult = commandDispatcher.dispatch(command);
        return tagListCommandResult.getListTag();
    }

    @Override
    public List<Tag> findListTag(Long projectId) {
        GetListTagsWithParamCommand command = new GetListTagsWithParamCommand(tagEntityRepository);
        command.setProjectId(projectId);
        TagListCommandResult tagListCommandResult = commandDispatcher.dispatch(command);
        return tagListCommandResult.getListTag();
    }

    @Override
    public List<String> findDescription(long tagId) {
        return errorCommentEntityRepository.findErrorsByTagId(tagId);
    }
}
