package ru.otus.commands;

import ru.otus.exceptions.CommandException;

import java.util.List;

public class MoveMacroCommand implements Command {

    private final List<Command> commands;

    public MoveMacroCommand(List<Command> commands) {
        this.commands = commands;
    }

    @Override
    public void execute() throws CommandException {
        for (int i = 0; i < commands.size(); i++) {
            commands.get(i).execute();
        }
    }
}
