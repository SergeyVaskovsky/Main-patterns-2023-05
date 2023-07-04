package ru.otus;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.otus.commands.BurnFuelCommand;
import ru.otus.commands.CheckFuelCommand;
import ru.otus.commands.MoveCommand;
import ru.otus.commands.MoveMacroCommand;
import ru.otus.exceptions.CommandException;
import ru.otus.move.MoveAdapter;
import ru.otus.utils.Consts;
import ru.otus.utils.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.otus.utils.Consts.*;

public class MoveMacroCommandTest {

    private final GameObject gameObjectMock = Mockito.mock(GameObject.class);
    private MoveMacroCommand moveMacroCommand;

    @BeforeEach
    void init() {
        MoveAdapter moveAdapter = new MoveAdapter(gameObjectMock);
        moveMacroCommand = new MoveMacroCommand(List.of(
                new CheckFuelCommand(gameObjectMock),
                new MoveCommand(moveAdapter),
                new BurnFuelCommand(gameObjectMock)
        ));
    }

    @Test
    void testMoveWithEnoughAmountOfFuel() throws CommandException {
        Mockito.when(gameObjectMock.getProperty(POSITION)).thenReturn(new Vector(12, 5), new Vector(5, 8));
        Mockito.when(gameObjectMock.getProperty(VELOCITY)).thenReturn(new Vector(-7, 3));
        Mockito.when(gameObjectMock.getProperty(FUEL_AMOUNT)).thenReturn(100, 100 - Consts.FUEL_AMOUNT_FOR_MOVE);
        moveMacroCommand.execute();
        assertEquals(100 - Consts.FUEL_AMOUNT_FOR_MOVE, gameObjectMock.getProperty(FUEL_AMOUNT));
        assertEquals(5, ((Vector) gameObjectMock.getProperty(POSITION)).getX());
        assertEquals(8, ((Vector) gameObjectMock.getProperty(POSITION)).getY());
    }

    @Test
    void testMoveWithNotEnoughAmountOfFuel() throws CommandException {
        Mockito.when(gameObjectMock.getProperty(POSITION)).thenReturn(new Vector(12, 5), new Vector(5, 8));
        Mockito.when(gameObjectMock.getProperty(VELOCITY)).thenReturn(new Vector(-7, 3));
        Mockito.when(gameObjectMock.getProperty(FUEL_AMOUNT)).thenReturn(9, 9 - Consts.FUEL_AMOUNT_FOR_MOVE);
        Exception exception = assertThrows(CommandException.class, () -> moveMacroCommand.execute());
    }

    @AfterEach
    void tearDown() {
        moveMacroCommand = null;
    }
}