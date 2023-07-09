package ru.otus;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.otus.commands.ChangeVelocityCommand;
import ru.otus.commands.RotateCommand;
import ru.otus.commands.RotateMacroCommand;
import ru.otus.exceptions.CommandException;
import ru.otus.rotate.RotateAdapter;
import ru.otus.utils.Vector;

import java.util.List;

import static ru.otus.utils.Consts.*;

public class RotateMacroCommandTest {

    private final GameObject gameObjectMock = Mockito.mock(GameObject.class);
    private RotateMacroCommand rotateMacroCommand;

    @BeforeEach
    void init() {
        RotateAdapter rotateAdapter = new RotateAdapter(gameObjectMock);
        rotateMacroCommand = new RotateMacroCommand(List.of(
                new RotateCommand(rotateAdapter),
                new ChangeVelocityCommand(gameObjectMock)
        ));
    }

    @Test
    void testMoveWithEnoughAmountOfFuel() throws CommandException {
        Mockito.when(gameObjectMock.getProperty(DIRECTION)).thenReturn(10);
        Mockito.when(gameObjectMock.getProperty(VELOCITY)).thenReturn(2);
        Mockito.when(gameObjectMock.getProperty(DIRECTIONS_NUMBER)).thenReturn(1);
        Mockito.when(gameObjectMock.getProperty(VECTOR_VELOCITY)).thenReturn(new Vector(1.9992284446749673, 0.055548411341017505));
        rotateMacroCommand.execute();
        Assertions.assertEquals(((Vector) gameObjectMock.getProperty(VECTOR_VELOCITY)).getX(), 1.9992284446749673);
        Assertions.assertEquals(((Vector) gameObjectMock.getProperty(VECTOR_VELOCITY)).getY(), 0.055548411341017505);
    }

    @AfterEach
    void tearDown() {
        rotateMacroCommand = null;
    }
}
