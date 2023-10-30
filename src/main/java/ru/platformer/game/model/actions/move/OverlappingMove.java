package ru.platformer.game.model.actions.move;

import com.badlogic.gdx.math.GridPoint2;
import ru.platformer.game.Direction;
import ru.platformer.game.model.CollisionDetector;
import ru.platformer.game.model.Movable;

public class OverlappingMove implements MoveStrategy {
    private final CollisionDetector collisionDetector;
    private final Direction direction;
    private final int levelWidth;
    private final int levelHeight;
    private boolean coordinatesReset = false;

    public OverlappingMove(Direction direction, CollisionDetector collisionDetector,
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
        if (isBorder(coordinates, levelWidth, levelHeight)) {
            resetCoordinates();
        }
    }

    private void resetCoordinates() {
        coordinatesReset = true;
    }
}
