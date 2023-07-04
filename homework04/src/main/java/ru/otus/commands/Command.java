package ru.otus.commands;

import ru.otus.exceptions.CommandException;

public interface Command {
    void execute() throws CommandException;
}
