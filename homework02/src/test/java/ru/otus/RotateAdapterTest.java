package ru.otus;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.otus.exceptions.CantSetPositionException;
import ru.otus.exceptions.PropertyNotFoundException;
import ru.otus.rotate.RotateAdapter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static ru.otus.utils.Consts.*;

public class RotateAdapterTest {

    private RotateAdapter rotateAdapter;
    private final GameObject gameObjectMock = Mockito.mock(GameObject.class);

    @BeforeEach
    void init() {
        rotateAdapter = new RotateAdapter(gameObjectMock);
    }

    @Test
    void testThatGameObjectShouldRotate() {
        Mockito.when(gameObjectMock.getProperty(DIRECTION)).thenReturn(10);
        Mockito.when(gameObjectMock.getProperty(ANGULAR_VELOCITY)).thenReturn(3);
        Mockito.when(gameObjectMock.getProperty(DIRECTIONS_NUMBER)).thenReturn(3);
        int result = rotateAdapter.rotate();
        assertEquals(result, 1);
    }

    @Test
    void testThatGameObjectDoesntHaveDirection() {
        String expectedMessage = "Направление не задано";
        Mockito.when(gameObjectMock.getProperty(DIRECTION)).thenThrow(new PropertyNotFoundException(expectedMessage));
        Mockito.when(gameObjectMock.getProperty(ANGULAR_VELOCITY)).thenReturn(3);
        Mockito.when(gameObjectMock.getProperty(DIRECTIONS_NUMBER)).thenReturn(3);
        Exception exception = assertThrows(PropertyNotFoundException.class, () -> rotateAdapter.rotate());
        String actualMessage = exception.getMessage();
        assertEquals(exception.getClass(), PropertyNotFoundException.class);
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testThatGameObjectDoesntHaveAngularVelocity() {
        String expectedMessage = "Угловая скорость не задана";
        Mockito.when(gameObjectMock.getProperty(DIRECTION)).thenReturn(10);
        Mockito.when(gameObjectMock.getProperty(ANGULAR_VELOCITY)).thenThrow(new PropertyNotFoundException(expectedMessage));
        Mockito.when(gameObjectMock.getProperty(DIRECTIONS_NUMBER)).thenReturn(3);
        Exception exception = assertThrows(PropertyNotFoundException.class, () -> rotateAdapter.rotate());
        String actualMessage = exception.getMessage();
        assertEquals(exception.getClass(), PropertyNotFoundException.class);
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testThatGameObjectDoesntHaveDirectionsNumber() {
        String expectedMessage = "Число направлений не задано";
        Mockito.when(gameObjectMock.getProperty(DIRECTION)).thenReturn(10);
        Mockito.when(gameObjectMock.getProperty(ANGULAR_VELOCITY)).thenReturn(3);
        Mockito.when(gameObjectMock.getProperty(DIRECTIONS_NUMBER)).thenThrow(new PropertyNotFoundException(expectedMessage));
        Exception exception = assertThrows(PropertyNotFoundException.class, () -> rotateAdapter.rotate());
        String actualMessage = exception.getMessage();
        assertEquals(exception.getClass(), PropertyNotFoundException.class);
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testThatGameObjectShouldNotRotate() {
        String expectedMessage = "Невозможно изменить направление в пространстве";
        Mockito.when(gameObjectMock.getProperty(DIRECTION)).thenReturn(10);
        Mockito.when(gameObjectMock.getProperty(ANGULAR_VELOCITY)).thenReturn(3);
        Mockito.when(gameObjectMock.getProperty(DIRECTIONS_NUMBER)).thenReturn(3);
        Mockito.doThrow(new CantSetPositionException(expectedMessage)).when(gameObjectMock).setProperty(any(), any());
        Exception exception = assertThrows(CantSetPositionException.class, () -> rotateAdapter.rotate());
        String actualMessage = exception.getMessage();
        assertEquals(exception.getClass(), CantSetPositionException.class);
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @AfterEach
    void tearDown() {
        rotateAdapter = null;
    }
}
