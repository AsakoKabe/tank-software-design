package ru.mipt.bit.platformer.action;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.gameEntities.GameEntity;
import ru.mipt.bit.platformer.gameEntities.Level;
import ru.mipt.bit.platformer.gameEntities.Movable;

public class MoveAction implements Action{

    private final Direction direction;
    private final Level level;
    private boolean coordinatesReset;

    public MoveAction(Direction direction, Level level) {
        this.direction = direction;
        this.coordinatesReset = false;
        this.level = level;
    }

    public void apply(GameEntity gameEntity) {
        Movable movableGameEntity = (Movable) gameEntity;
        movableGameEntity.moveToDirection(direction, coordinatesReset);
        coordinatesReset = false;
    }

    private void resetCoordinates(){
        coordinatesReset = true;
    }

    public Direction getDirection() {
        return direction;
    }

    public boolean validate(GameEntity gameEntity) {
        GridPoint2 coordinates = direction.applyCoordinates(gameEntity.getCurrentCoordinates());
        if (level.collidesExist(coordinates)){
            resetCoordinates();
        }

        return true;
    }
}
