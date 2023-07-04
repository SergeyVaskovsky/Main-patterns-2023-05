package ru.otus;

import ru.otus.exceptions.CantSetPositionException;
import ru.otus.exceptions.PropertyNotFoundException;

public interface GameObject {
    Object getProperty(String key) throws PropertyNotFoundException;
    void setProperty(String key, Object newValue) throws CantSetPositionException;
}
