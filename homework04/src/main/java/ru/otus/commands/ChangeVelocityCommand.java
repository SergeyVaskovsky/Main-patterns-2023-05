package ru.otus.commands;

import ru.otus.GameObject;
import ru.otus.exceptions.CommandException;
import ru.otus.utils.Vector;

import static ru.otus.utils.Consts.*;

public class ChangeVelocityCommand implements Command {

    private final GameObject gameObject;

    public ChangeVelocityCommand(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    @Override
    public void execute() throws CommandException {
        int d = (int) gameObject.getProperty(DIRECTION);
        int n = (int) gameObject.getProperty(DIRECTIONS_NUMBER);
        int v = (int) gameObject.getProperty(VELOCITY);
        gameObject.setProperty(VECTOR_VELOCITY,
                new Vector(
                        v * Math.cos((double) d / 360 * n),
                        v * Math.sin((double) d / 360 * n)
                ));
    }
}
