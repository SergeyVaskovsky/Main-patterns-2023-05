package ru.otus;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.otus.exceptions.CantSetPositionException;
import ru.otus.exceptions.PropertyNotFoundException;
import ru.otus.move.MoveAdapter;
import ru.otus.utils.Vector;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static ru.otus.utils.Consts.POSITION;
import static ru.otus.utils.Consts.VELOCITY;

public class MoveAdapterTest {
    private MoveAdapter moveAdapter;
    private final GameObject gameObjectMock = Mockito.mock(GameObject.class);

    @BeforeEach
    void init() {
        moveAdapter = new MoveAdapter(gameObjectMock);
    }

    @Test
    void testThatGameObjectShouldMove() throws PropertyNotFoundException {
        Mockito.when(gameObjectMock.getProperty(POSITION)).thenReturn(new Vector(12,5));
        Mockito.when(gameObjectMock.getProperty(VELOCITY)).thenReturn(new Vector(-7,3));
        Vector result = moveAdapter.move();
        assertEquals(result.getX(), 5);
        assertEquals(result.getY(), 8);
    }

    @Test
    void testThatGameObjectDoesntHavePosition() throws PropertyNotFoundException {
        String expectedMessage = "Положение в пространстве не задано";
        Mockito.when(gameObjectMock.getProperty(POSITION)).thenThrow(new PropertyNotFoundException(expectedMessage));
        Mockito.when(gameObjectMock.getProperty(VELOCITY)).thenReturn(new Vector(1, 1));
        Exception exception = assertThrows(PropertyNotFoundException.class, () -> moveAdapter.move());
        String actualMessage = exception.getMessage();
        assertEquals(exception.getClass(), PropertyNotFoundException.class);
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testThatGameObjectDoesntHaveVelocity() throws PropertyNotFoundException {
        String expectedMessage = "Скорость не задана";
        Mockito.when(gameObjectMock.getProperty(POSITION)).thenReturn(new Vector(10,10));
        Mockito.when(gameObjectMock.getProperty(VELOCITY)).thenThrow(new PropertyNotFoundException(expectedMessage));
        Exception exception = assertThrows(PropertyNotFoundException.class, () -> moveAdapter.move());
        String actualMessage = exception.getMessage();
        assertEquals(exception.getClass(), PropertyNotFoundException.class);
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testThatGameObjectShouldNotMove() throws PropertyNotFoundException {
        String expectedMessage = "Невозможно изменить положение в пространстве";
        Mockito.when(gameObjectMock.getProperty(POSITION)).thenReturn(new Vector(10,10));
        Mockito.when(gameObjectMock.getProperty(VELOCITY)).thenReturn(new Vector(10,10));
        Mockito.doThrow(new CantSetPositionException(expectedMessage)).when(gameObjectMock).setProperty(any(), any());
        Exception exception = assertThrows(CantSetPositionException.class, () -> moveAdapter.move());
        String actualMessage = exception.getMessage();
        assertEquals(exception.getClass(), CantSetPositionException.class);
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @AfterEach
    void tearDown() {
        moveAdapter = null;
    }
}
