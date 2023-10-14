package ru.platformer.game;

import com.badlogic.gdx.math.GridPoint2;

public interface GameObject {

    GridPoint2 getCurrentCoordinates();
    void updateState(float deltaTime);

}
