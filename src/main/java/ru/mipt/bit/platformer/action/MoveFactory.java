package ru.mipt.bit.platformer.action;

public class MoveFactory implements ActionFactory{
    private final Direction direction;

    public MoveFactory(Direction direction) {
        this.direction = direction;
    }

    public MoveAction create(){
        return new MoveAction(direction);
    }
}
