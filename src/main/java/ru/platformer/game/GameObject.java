package ru.platformer.game;

import com.badlogic.gdx.math.GridPoint2;

public interface GameObject {

    GridPoint2 getCurrentCoordinates();
    boolean isBusyCoordinate(GridPoint2 coordinates);
    void updateState(float deltaTime);

}
