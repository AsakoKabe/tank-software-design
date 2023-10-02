package ru.mipt.bit.platformer.gameEntities;

import com.badlogic.gdx.math.GridPoint2;

public interface GameEntity {

    GridPoint2 getCurrentCoordinates();
    void updateState(float deltaTime);

}
