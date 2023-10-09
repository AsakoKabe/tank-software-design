package ru.platformer.game.model.actions.move;

import com.badlogic.gdx.math.GridPoint2;
import ru.platformer.game.Action;
import ru.platformer.game.Direction;
import ru.platformer.game.model.Level;
import ru.platformer.game.model.Movable;

public class MoveAction implements Action {

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
