package ru.platformer.game.model;

import com.badlogic.gdx.math.GridPoint2;
import ru.platformer.game.Direction;

public interface Movable {
    float MOVEMENT_COMPLETED = 1f;
    int MOVEMENT_STARTED = 0;

    GridPoint2 getDestinationCoordinates();
    GridPoint2 getCurrentCoordinates();

    Direction getDirection();

    void moveTo(Direction direction);
    void rotate(Direction direction);


}
