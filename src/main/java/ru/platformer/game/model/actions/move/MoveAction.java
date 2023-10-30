package ru.platformer.game.model.actions.move;

import com.badlogic.gdx.math.GridPoint2;
import ru.platformer.game.Action;
import ru.platformer.game.Direction;
import ru.platformer.game.GameObject;
import ru.platformer.game.model.CollisionDetector;
import ru.platformer.game.model.Movable;

public class MoveAction implements Action {

    private final Movable movable;
    private final MoveStrategy moveStrategy;

    public MoveAction(
            MoveStrategy moveStrategy,
            Movable movable
    ) {
        this.movable = movable;
        this.moveStrategy = moveStrategy;
    }

    public void apply() {
        moveStrategy.move(movable);
    }

}
