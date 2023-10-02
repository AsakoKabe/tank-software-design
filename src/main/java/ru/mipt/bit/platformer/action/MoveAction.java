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
        preprocessingIfCollides(gameEntity);

        Movable movableGameEntity = (Movable) gameEntity;
        movableGameEntity.moveToDirection(direction, coordinatesReset);
    }

    private void resetCoordinates(){
        coordinatesReset = true;
    }

    private void preprocessingIfCollides(GameEntity gameEntity) {
        GridPoint2 coordinates = direction.applyCoordinates(gameEntity.getCurrentCoordinates());
        if (level.collidesExist(coordinates)){
            resetCoordinates();
        }
    }

    public Direction getDirection() {
        return direction;
    }

}
