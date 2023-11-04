package ru.platformer.game.model.actions.move;

import com.badlogic.gdx.math.GridPoint2;
import ru.platformer.game.Action;
import ru.platformer.game.Direction;
import ru.platformer.game.GameObject;
import ru.platformer.game.model.CollisionDetector;
import ru.platformer.game.model.Movable;

public class MoveAction implements Action {

    private final Movable movable;
    private final Direction direction;
    private final CollisionDetector collisionDetector;
    private boolean coordinatesReset = false;
    private final int levelWidth;
    private final int levelHeight;

    public MoveAction(
            Movable movable,
            Direction direction,
            CollisionDetector collisionDetector,
            int levelWidth,
            int levelHeight
    ) {
        this.movable = movable;
        this.direction = direction;
        this.collisionDetector = collisionDetector;
        this.levelWidth = levelWidth;
        this.levelHeight = levelHeight;    }

    public void apply() {
        preprocessingCoordinates(movable);
        movable.moveToDirection(direction, coordinatesReset);
    }

    private void preprocessingCoordinates(Movable movable) {
        GridPoint2 coordinates = direction.applyCoordinates(movable.getCurrentCoordinates());
        if (isCollisionExist(coordinates)) {
            resetCoordinates();
        }
        if (isBorder(coordinates, levelWidth, levelHeight)) {
            resetCoordinates();
        }
    }

    private void resetCoordinates() {
        coordinatesReset = true;
    }

    private boolean isCollisionExist(GridPoint2 coordinates) {
        return collisionDetector.collisionExist(coordinates);
    }
    private boolean isBorder(GridPoint2 coordinates, int levelWidth, int levelHeight) {
        return coordinates.x > levelWidth || coordinates.x < 0 ||
                coordinates.y < 0 || coordinates.y > levelHeight;
    }


}
