package ru.otus;

import java.util.Map;

public interface Command {
    void execute() throws Exception;

    void setParameters(Map<String, Object> parameters);
}
