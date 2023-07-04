package ru.otus.commands;

import ru.otus.rotate.RotateAdapter;

public class RotateCommand implements Command{

    private final RotateAdapter rotateAdapter;

    public RotateCommand(RotateAdapter rotateAdapter) {
        this.rotateAdapter = rotateAdapter;
    }

    @Override
    public void execute() {
        rotateAdapter.rotate();
    }
}
