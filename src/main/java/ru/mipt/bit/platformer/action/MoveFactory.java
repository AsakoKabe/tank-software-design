package ru.mipt.bit.platformer.action;

import ru.mipt.bit.platformer.gameEntities.Level;

public class MoveFactory implements ActionFactory{
    private final Direction direction;
    private final Level level;

    public MoveFactory(Direction direction, Level level) {
        this.direction = direction;
        this.level = level;
    }

    public MoveAction create(){
        return new MoveAction(direction, level);
    }
}
