package ru.otus.move;

import ru.otus.utils.Vector;

public interface Movable {
    Vector getPosition();
    Vector getVelocity();
    void setPosition(Vector newVector);
}
