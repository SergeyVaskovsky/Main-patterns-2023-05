package ru.otus;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.otus.commands.BurnFuelCommand;
import ru.otus.exceptions.CommandException;
import ru.otus.utils.Consts;

import static ru.otus.utils.Consts.FUEL_AMOUNT;

public class BurnFuelCommandTest {

    private BurnFuelCommand burnFuelCommand;
    private final GameObject gameObjectMock = Mockito.mock(GameObject.class);

    @BeforeEach
    void init() {
        burnFuelCommand = new BurnFuelCommand(gameObjectMock);
    }

    @Test
    void testBurnFuel() throws CommandException {
        Mockito.when(gameObjectMock.getProperty(FUEL_AMOUNT)).thenReturn(100, 100 - Consts.FUEL_AMOUNT_FOR_MOVE);
        burnFuelCommand.execute();
        Assertions.assertEquals(100 - Consts.FUEL_AMOUNT_FOR_MOVE, gameObjectMock.getProperty(FUEL_AMOUNT));
    }

    @AfterEach
    void tearDown() {
        burnFuelCommand = null;
    }
}
