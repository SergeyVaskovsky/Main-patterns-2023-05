package ru.otus.move;

import ru.otus.GameObject;
import ru.otus.exceptions.CantSetPositionException;
import ru.otus.exceptions.PropertyNotFoundException;
import ru.otus.utils.Vector;

import static ru.otus.utils.Consts.POSITION;
import static ru.otus.utils.Consts.VELOCITY;

public class MoveAdapter implements Movable {

    private final GameObject gameObject;

    public MoveAdapter(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    public Vector move() throws PropertyNotFoundException {
        Vector result = Vector.plus(getPosition(), getVelocity());
        setPosition(result);
        return result;
    }

    @Override
    public Vector getPosition() throws PropertyNotFoundException {
        return (Vector) gameObject.getProperty(POSITION);
    }

    @Override
    public Vector getVelocity() throws PropertyNotFoundException {
        return (Vector) gameObject.getProperty(VELOCITY);
    }

    @Override
    public void setPosition(Vector newVector) throws CantSetPositionException {
        gameObject.setProperty(POSITION, newVector);
    }
}
