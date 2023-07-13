package ru.otus.commands;

import ru.otus.GameObject;
import ru.otus.exceptions.CommandException;

import static ru.otus.utils.Consts.FUEL_AMOUNT;
import static ru.otus.utils.Consts.FUEL_AMOUNT_FOR_MOVE;

public class BurnFuelCommand implements Command {

    private final GameObject gameObject;

    public BurnFuelCommand(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    @Override
    public void execute() throws CommandException {
        int fuelAmount = (int) gameObject.getProperty(FUEL_AMOUNT);
        fuelAmount -= FUEL_AMOUNT_FOR_MOVE;
        gameObject.setProperty(FUEL_AMOUNT, fuelAmount);
    }
}
