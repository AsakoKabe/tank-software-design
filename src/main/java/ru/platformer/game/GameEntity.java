package ru.platformer.game;

import com.badlogic.gdx.math.GridPoint2;

public interface GameEntity {

    GridPoint2 getCurrentCoordinates();
    void updateState(float deltaTime);

}
