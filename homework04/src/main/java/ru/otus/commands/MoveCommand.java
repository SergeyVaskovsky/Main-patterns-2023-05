package ru.otus.commands;

import ru.otus.move.MoveAdapter;

public class MoveCommand implements Command{

    private final MoveAdapter moveAdapter;

    public MoveCommand(MoveAdapter moveAdapter) {
        this.moveAdapter = moveAdapter;
    }

    @Override
    public void execute() {
        moveAdapter.move();
    }
}
