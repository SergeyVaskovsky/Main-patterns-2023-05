package ru.otus.command;

import ru.otus.Command;
import ru.otus.GameObject;

public class StartNewThreadCommand implements Command {

    private final GameObject gameObject;

    public StartNewThreadCommand(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    @Override
    public void execute() {
        gameObject.addNewThread();
    }
}
