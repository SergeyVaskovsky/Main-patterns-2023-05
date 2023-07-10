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

    private static final double EPSILON = 0.000_000_1;

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
    void testRotateWithInstantVelocity() throws CommandException {
        Mockito.when(gameObjectMock.getProperty(DIRECTION)).thenReturn(10, 1);
        Mockito.when(gameObjectMock.getProperty(ANGULAR_VELOCITY)).thenReturn(3);
        Mockito.when(gameObjectMock.getProperty(DIRECTIONS_NUMBER)).thenReturn(3);
        Mockito.when(gameObjectMock.getProperty(VELOCITY)).thenReturn(3);
        Mockito.when(gameObjectMock.getProperty(VECTOR_VELOCITY)).thenReturn(new Vector(1.9992284446749673, 0.055548411341017505));
        rotateMacroCommand.execute();
        Assertions.assertEquals(gameObjectMock.getProperty(DIRECTION), 1);
        Assertions.assertTrue(
                Math.abs(((Vector) gameObjectMock.getProperty(VECTOR_VELOCITY)).getX() - 1.9992284446749673) < EPSILON);
        Assertions.assertTrue(
                Math.abs(((Vector) gameObjectMock.getProperty(VECTOR_VELOCITY)).getY() - 0.055548411341017505) < EPSILON);
    }

    @AfterEach
    void tearDown() {
        rotateMacroCommand = null;
    }
}
