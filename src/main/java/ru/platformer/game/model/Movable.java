package ru.platformer.game.model;

import com.badlogic.gdx.math.GridPoint2;
import ru.platformer.game.Direction;

public interface Movable {
    void moveToDirection(Direction direction, Boolean onlyRotation);

    GridPoint2 getDestinationCoordinates();
    GridPoint2 getCurrentCoordinates();

}
