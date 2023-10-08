package ru.mipt.bit.platformer.action;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.gameEntities.Level;
import ru.mipt.bit.platformer.gameEntities.Movable;

public class MoveAction implements Action{

    private final Direction direction;
    private final Level level;
    private final Movable movable;
    private boolean coordinatesReset;

    public MoveAction(Direction direction, Level level, Movable movable) {
        this.direction = direction;
        this.coordinatesReset = false;
        this.level = level;
        this.movable = movable;
    }

    public void apply() {
        preprocessingIfCollides();
        movable.moveToDirection(direction, coordinatesReset);
    }

    private void resetCoordinates(){
        coordinatesReset = true;
    }

    private void preprocessingIfCollides() {
        GridPoint2 coordinates = direction.applyCoordinates(movable.getCurrentCoordinates());
        if (level.collisionExist(coordinates)){
            resetCoordinates();
        }
    }

    public Direction getDirection() {
        return direction;
    }

}
