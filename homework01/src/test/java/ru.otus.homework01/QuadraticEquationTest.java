package ru.otus.homework01;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.otus.homework01.exception.ArgumentException;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class QuadraticEquationTest {
    private QuadraticEquation quadraticEquation;

    @BeforeEach
    void init() {
        quadraticEquation = new QuadraticEquation();
    }

    private static Stream<Arguments> provideParamsForTest() {
        return Stream.of(
                Arguments.of(Double.NaN, 10, 20, String.format(QuadraticEquation.PARAMETER_NOT_VALUE, "a")),
                Arguments.of(2, Double.POSITIVE_INFINITY, 1, String.format(QuadraticEquation.PARAMETER_NOT_VALUE, "b")),
                Arguments.of(2, 1, Double.NEGATIVE_INFINITY, String.format(QuadraticEquation.PARAMETER_NOT_VALUE, "c"))
        );
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

    @ParameterizedTest
    @MethodSource("provideParamsForTest")
    void testThatParametersNan(double a, double b, double c, String result) {
        Exception exception = assertThrows(ArgumentException.class, () -> quadraticEquation.solve(a, b, c));

        String expectedMessage = result;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @AfterEach
    void tearDown() {
        quadraticEquation = null;
    }
}