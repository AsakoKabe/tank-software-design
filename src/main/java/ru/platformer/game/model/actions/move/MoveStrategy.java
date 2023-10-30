package ru.platformer.game.model.actions.move;

import com.badlogic.gdx.math.GridPoint2;
import ru.platformer.game.model.Movable;

public interface MoveStrategy {

    default boolean isBorder(GridPoint2 coordinates, int levelWidth, int levelHeight) {
        return coordinates.x > levelWidth || coordinates.x < 0 ||
                coordinates.y < 0 || coordinates.y > levelHeight;
    }

    void move(Movable movable);
}
