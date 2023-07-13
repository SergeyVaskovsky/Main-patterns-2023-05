package ru.otus.rotate;

import ru.otus.GameObject;

import static ru.otus.utils.Consts.*;

public class RotateAdapter implements Rotable {

    private final GameObject gameObject;

    public RotateAdapter(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    public int rotate() {
        int result = (getDirection() + getAngularVelocity()) % getDirectionsNumber();
        setDirection(result);
        return result;
    }

    @Override
    public int getDirection() {
        return (int) gameObject.getProperty(DIRECTION);
    }

    @Override
    public int getAngularVelocity() {
        return (int) gameObject.getProperty(ANGULAR_VELOCITY);
    }

    @Override
    public void setDirection(int newValue) {
        gameObject.setProperty(DIRECTION, newValue);
    }

    @Override
    public int getDirectionsNumber() {
        return (int) gameObject.getProperty(DIRECTIONS_NUMBER);
    }


}
