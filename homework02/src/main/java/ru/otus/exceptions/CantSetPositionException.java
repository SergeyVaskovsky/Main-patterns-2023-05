package ru.otus.exceptions;

public class CantSetPositionException extends RuntimeException {
    public CantSetPositionException(String message) {
        super(message);
    }
}
