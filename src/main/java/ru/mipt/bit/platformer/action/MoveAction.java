package ru.mipt.bit.platformer.action;

import ru.mipt.bit.platformer.gameEntities.GameEntity;
import ru.mipt.bit.platformer.gameEntities.Movable;

public class MoveAction implements Action{

    private final Direction direction;
    private boolean coordinatesReset;

    public MoveAction(Direction direction) {
        this.direction = direction;
        this.coordinatesReset = false;
    }

    public void apply(GameEntity gameEntity) {
        Movable movableGameEntity = (Movable) gameEntity;
        movableGameEntity.moveToDirection(direction, coordinatesReset);
        coordinatesReset = false;
    }

    public void resetCoordinates(){
        coordinatesReset = true;
    }

    public Direction getDirection() {
        return direction;
    }
}
