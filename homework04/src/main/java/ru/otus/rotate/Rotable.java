package ru.otus.rotate;

public interface Rotable {
    int getDirection();
    int getAngularVelocity();
    void setDirection(int newValue);
    int getDirectionsNumber();
}
