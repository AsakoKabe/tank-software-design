package ru.platformer.game.model.actions.move;

import ru.platformer.game.Direction;
import ru.platformer.game.GameObject;
import ru.platformer.game.model.Level;
import ru.platformer.game.model.Movable;
import ru.platformer.game.ActionFactory;

public class MoveFactory implements ActionFactory {
    private final Direction direction;
    private final Level level;

    public MoveFactory(Direction direction, Level level) {
        this.direction = direction;
        this.level = level;
    }

    public MoveAction create(GameObject gameEntity){
        return new MoveAction(direction, level, (Movable) gameEntity);
    }
}
