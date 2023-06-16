package ru.otus.homework01;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.otus.homework01.exception.ArgumentException;
import ru.otus.homework01.exception.CalculatingException;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class QuadraticEquationTest {
    private QuadraticEquation quadraticEquation;

    @BeforeEach
    void init() {
        quadraticEquation = new QuadraticEquation();
    }

    @Test
    void testThatNoRoots() {
        double a = 1;
        double b = 0;
        double c = 1;
        double[] result = quadraticEquation.solve(a, b, c);
        assertEquals(result.length, 0);
    }

    @Test
    void testThatTwoRoots() {
        double a = 1;
        double b = 0;
        double c = -1;
        double[] result = quadraticEquation.solve(a, b, c);
        assertEquals(result.length, 2);
        assertTrue(Math.abs(result[0] - 1) < QuadraticEquation.EPSILON);
        assertTrue(Math.abs(result[1] + 1) < QuadraticEquation.EPSILON);
    }

    @Test
    void testThatOneRoot() {
        double a = 1;
        double b = 2;
        double c = 1;
        double[] result = quadraticEquation.solve(a, b, c);
        assertEquals(result.length, 2);
        assertTrue(Math.abs(result[0] + 1) < QuadraticEquation.EPSILON);
        assertTrue(Math.abs(result[1] + 1) < QuadraticEquation.EPSILON);
    }

    @Test
    void testThatADoesntEqualZero() {
        double a = 0;
        double b = 2;
        double c = 1;

        Exception exception = assertThrows(ArgumentException.class, () -> quadraticEquation.solve(a, b, c));

        String expectedMessage = QuadraticEquation.A_IS_ZERO;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testThatOneRootWithMicroValues() {
        double a = 0.000_000_2;
        double b = 0.000_000_000_1;
        double c = 0.000_000_000_1;
        double[] result = quadraticEquation.solve(a, b, c);
        assertEquals(result.length, 2);
        assertTrue(Math.abs(result[0] + 2.5E-4) < QuadraticEquation.EPSILON);
        assertTrue(Math.abs(result[1] + 2.5E-4) < QuadraticEquation.EPSILON);
    }

    private static Stream<Arguments> provideParamsForTest() {
        return Stream.of(
                arguments(Double.NaN, 10, 20, String.format(QuadraticEquation.PARAMETER_NOT_VALUE, "a")),
                arguments(2, Double.POSITIVE_INFINITY, 1, String.format(QuadraticEquation.PARAMETER_NOT_VALUE, "b")),
                arguments(2, 1, Double.NEGATIVE_INFINITY, String.format(QuadraticEquation.PARAMETER_NOT_VALUE, "c"))
        );
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTest")
    void testThatParametersNan(double a, double b, double c, String expectedMessage) {
        Exception exception = assertThrows(ArgumentException.class, () -> quadraticEquation.solve(a, b, c));
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testWhenBIsMaxValue() {
        double a = 1;
        double b = Double.MAX_VALUE;
        double c = 1;
        Exception exception = assertThrows(CalculatingException.class, () -> quadraticEquation.solve(a, b, c));

        String expectedMessage = QuadraticEquation.D_IS_GREATER_THEN_MAX;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }


    @AfterEach
    void tearDown() {
        quadraticEquation = null;
    }
}