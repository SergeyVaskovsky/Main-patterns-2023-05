package ru.otus;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.otus.commands.ChangeVelocityCommand;
import ru.otus.exceptions.CommandException;
import ru.otus.utils.Vector;

import static ru.otus.utils.Consts.*;

public class ChangeVelocityCommandTest {

    private final GameObject gameObjectMock = Mockito.mock(GameObject.class);
    private ChangeVelocityCommand changeVelocityCommand;

    @BeforeEach
    void init() {
        changeVelocityCommand = new ChangeVelocityCommand(gameObjectMock);
    }

    @Test
    void testCalculationVelocity() throws CommandException {
        Mockito.when(gameObjectMock.getProperty(DIRECTION)).thenReturn(10);
        Mockito.when(gameObjectMock.getProperty(VELOCITY)).thenReturn(2);
        Mockito.when(gameObjectMock.getProperty(DIRECTIONS_NUMBER)).thenReturn(1);
        Mockito.when(gameObjectMock.getProperty(VECTOR_VELOCITY)).thenReturn(new Vector(1.9992284446749673, 0.055548411341017505));
        changeVelocityCommand.execute();
        Assertions.assertEquals(((Vector) gameObjectMock.getProperty(VECTOR_VELOCITY)).getX(), 1.9992284446749673);
        Assertions.assertEquals(((Vector) gameObjectMock.getProperty(VECTOR_VELOCITY)).getY(), 0.055548411341017505);
    }

    @AfterEach
    void tearDown() {
        changeVelocityCommand = null;
    }
}
