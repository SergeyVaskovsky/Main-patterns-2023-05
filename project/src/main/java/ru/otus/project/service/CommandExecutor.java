package ru.otus.project.service;

public interface CommandExecutor<C extends Command, R extends CommandResult> {
    R execute(C command);
}