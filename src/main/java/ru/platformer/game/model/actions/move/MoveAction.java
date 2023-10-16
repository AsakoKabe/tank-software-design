package ru.platformer.game.model.actions.move;

import com.badlogic.gdx.math.GridPoint2;
import ru.platformer.game.Action;
import ru.platformer.game.Direction;
import ru.platformer.game.model.CollisionDetector;
import ru.platformer.game.model.Level;
import ru.platformer.game.model.Movable;

public class MoveAction implements Action {

    private final Direction direction;
    private final CollisionDetector collisionDetector;
    private final Movable movable;
    private boolean coordinatesReset;

    public MoveAction(Direction direction, CollisionDetector collisionDetector, Movable movable) {
        this.direction = direction;
        this.coordinatesReset = false;
        this.collisionDetector = collisionDetector;
        this.movable = movable;
    }

    public void apply() {
        preprocessingIfCollides();
        movable.moveToDirection(direction, coordinatesReset);
        collisionDetector.addCoordinates(movable.getDestinationCoordinates());
    }

    private void resetCoordinates(){
        coordinatesReset = true;
    }

    private void preprocessingIfCollides() {
        GridPoint2 coordinates = direction.applyCoordinates(movable.getCurrentCoordinates());
        if (collisionDetector.collisionExist(coordinates)){
            resetCoordinates();
        }
    }

}
