package ru.mipt.bit.platformer.action;

import ru.mipt.bit.platformer.gameEntities.GameEntity;
import ru.mipt.bit.platformer.gameEntities.Level;
import ru.mipt.bit.platformer.gameEntities.Movable;

public class MoveFactory implements ActionFactory{
    private final Direction direction;
    private final Level level;

    public MoveFactory(Direction direction, Level level) {
        this.direction = direction;
        this.level = level;
    }

    public MoveAction create(GameEntity gameEntity){
        return new MoveAction(direction, level, (Movable) gameEntity);
    }
}
