package ru.otus;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.otus.commands.CheckFuelCommand;
import ru.otus.exceptions.CommandException;
import ru.otus.exceptions.PropertyNotFoundException;
import ru.otus.utils.Vector;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.otus.utils.Consts.FUEL_AMOUNT;
import static ru.otus.utils.Consts.POSITION;

public class CheckFuelCommandTest {

    private CheckFuelCommand checkFuelCommand;
    private final GameObject gameObjectMock = Mockito.mock(GameObject.class);

    @BeforeEach
    void init() {
        checkFuelCommand = new CheckFuelCommand(gameObjectMock);
    }

    @Test
    void testThatFuelIsEnough() throws CommandException {
        Mockito.when(gameObjectMock.getProperty(FUEL_AMOUNT)).thenReturn(100);
        checkFuelCommand.execute();
    }

    @Test
    void testThatFuelIsNotEnough() throws CommandException {
        Mockito.when(gameObjectMock.getProperty(FUEL_AMOUNT)).thenReturn(9);
        Exception exception = assertThrows(CommandException.class, () -> checkFuelCommand.execute());
    }

    @AfterEach
    void tearDown() {
        checkFuelCommand = null;
    }
}
