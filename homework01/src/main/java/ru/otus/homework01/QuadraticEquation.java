package ru.otus.homework01;

import ru.otus.homework01.exception.ArgumentException;
import ru.otus.homework01.exception.CalculatingException;

public class QuadraticEquation {

    public static final double EPSILON = 0.000_000_1;
    public static final String A_IS_ZERO_MESSAGE = "a равно 0";
    public static final String PARAMETER_NOT_VALUE_MESSAGE = "Параметр %s не число";
    public static final String D_IS_GREATER_THEN_MAX_MESSAGE = "В результате вычислений превышено максимальное значение";

    public double[] solve(double a, double b, double c) {

        checkForNan(a, "a");
        checkForNan(b, "b");
        checkForNan(c, "c");

        if (Math.abs(a) <= EPSILON) {
            throw new ArgumentException(A_IS_ZERO_MESSAGE);
        }

        double d = b * b - 4 * a * c;

        if (Double.isInfinite(d)) {
            throw new CalculatingException(D_IS_GREATER_THEN_MAX_MESSAGE);
        }

        if (d < -EPSILON) {
            return new double[0];
        }

        if (d > EPSILON) {
            return new double[]{(-b + Math.sqrt(d)) / (2 * a), (-b - Math.sqrt(d)) / (2 * a)};
        }

        if (Math.abs(d) <= EPSILON) {
            return new double[]{-b / (2 * a), -b / (2 * a)};
        }

        return new double[0];
    }

    private void checkForNan(double value, String parameterName) {
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new ArgumentException(String.format(PARAMETER_NOT_VALUE_MESSAGE, parameterName));
        }
    }
}
