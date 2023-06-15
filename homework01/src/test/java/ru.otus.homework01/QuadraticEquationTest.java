package ru.otus.homework01;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuadraticEquationTest {
    private QuadraticEquation quadraticEquation;

    @BeforeEach
    void init() {
        quadraticEquation = new QuadraticEquation();
    }

    @Test
    void test() {
        double a = 1;
        double b = 0;
        double c = 1;
        double[] result = quadraticEquation.solve(a, b, c);
        assertEquals(result.length, 0);
    }


    @AfterEach
    void tearDown() {
        quadraticEquation = null;
    }
}