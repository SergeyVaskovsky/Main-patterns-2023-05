package ru.otus.homework01;

import ru.otus.homework01.exception.ArgumentException;

public class QuadraticEquation {

    public static final double EPSILON = 0.000_000_1;
    public static final String A_IS_ZERO = "a равно 0";
    public static final String PARAMETER_NOT_VALUE = "Параметр %s не число";

    public double[] solve(double a, double b, double c) {

        checkForNan(a, "a");
        checkForNan(b, "b");
        checkForNan(c, "c");

        if (Math.abs(a) <= EPSILON) {
            throw new ArgumentException(A_IS_ZERO);
        }

        double d = b * b - 4 * a * c;

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
            throw new ArgumentException(String.format(PARAMETER_NOT_VALUE, parameterName));
        }
    }
}
