package ru.otus.utils;

public class Vector {
    private double x;
    private double y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static Vector plus(Vector vectorOne, Vector vectorTwo) {
        return new Vector(vectorOne.x + vectorTwo.x, vectorOne.y + vectorTwo.y);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
