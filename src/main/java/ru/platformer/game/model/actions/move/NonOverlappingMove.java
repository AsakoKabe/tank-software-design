package ru.platformer.game.model.actions.move;

import com.badlogic.gdx.math.GridPoint2;
import ru.platformer.game.Direction;
import ru.platformer.game.GameObject;
import ru.platformer.game.model.CollisionDetector;
import ru.platformer.game.model.Movable;

public class NonOverlappingMove implements MoveStrategy{

    private final Direction direction;
    private final CollisionDetector collisionDetector;
    private boolean coordinatesReset = false;
    private final int levelWidth;
    private final int levelHeight;

    public NonOverlappingMove(Direction direction, CollisionDetector collisionDetector,
                              int levelWidth, int levelHeight) {
        this.direction = direction;
        this.collisionDetector = collisionDetector;
        this.levelWidth = levelWidth;
        this.levelHeight = levelHeight;
    }

    @Override
    public void move(Movable movable) {
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


}
