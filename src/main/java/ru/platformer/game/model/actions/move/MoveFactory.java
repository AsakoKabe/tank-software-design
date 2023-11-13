package ru.platformer.game.model.actions.move;

import ru.platformer.game.ActionFactory;
import ru.platformer.game.Direction;
import ru.platformer.game.GameObject;
import ru.platformer.game.model.CollisionDetector;
import ru.platformer.game.model.Movable;

public class MoveFactory implements ActionFactory {
    private final Direction direction;
    private final CollisionDetector collisionDetector;
    private final int levelWidth;
    private final int levelHeight;


    public MoveFactory(
            Direction direction,
            CollisionDetector collisionDetector,
            int levelWidth,
            int levelHeight
    ) {
        this.direction = direction;
        this.collisionDetector = collisionDetector;
        this.levelHeight = levelHeight;
        this.levelWidth = levelWidth;
    }

    public MoveAction create(GameObject gameObject) {
        return new MoveAction(
                (Movable) gameObject,
                direction,
                collisionDetector,
                levelWidth,
                levelHeight
        );
    }
}
