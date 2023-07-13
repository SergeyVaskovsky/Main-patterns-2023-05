package ru.otus.commands;

import ru.otus.GameObject;
import ru.otus.exceptions.CommandException;

import static ru.otus.utils.Consts.*;

public class CheckFuelCommand implements Command {

    private final GameObject gameObject;

    public CheckFuelCommand(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    @Override
    public void execute() throws CommandException {
        int fuelAmount = (int) gameObject.getProperty(FUEL_AMOUNT);
        if (fuelAmount < FUEL_AMOUNT_FOR_MOVE) {
            throw new CommandException();
        }
    }
}
