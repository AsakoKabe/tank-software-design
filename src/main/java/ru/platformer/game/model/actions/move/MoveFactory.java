package ru.platformer.game.model.actions.move;

import ru.platformer.game.Direction;
import ru.platformer.game.GameObject;
import ru.platformer.game.model.CollisionDetector;
import ru.platformer.game.ActionFactory;
import ru.platformer.game.model.Movable;

public class MoveFactory implements ActionFactory {
    private final Direction direction;
    private final CollisionDetector collisionDetector;

    public MoveFactory(Direction direction, CollisionDetector collisionDetector) {
        this.direction = direction;
        this.collisionDetector = collisionDetector;
    }

    public MoveAction create(GameObject gameObject){
        return new MoveAction(direction, collisionDetector, (Movable) gameObject);
    }
}
