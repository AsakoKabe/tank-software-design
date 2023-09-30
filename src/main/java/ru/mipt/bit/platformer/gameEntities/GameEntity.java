package ru.mipt.bit.platformer.gameEntities;

import com.badlogic.gdx.math.GridPoint2;

public interface GameEntity {

    public void updateState(float deltaTime);

    public void moveToDirection(GridPoint2 coordinates, float rotation);

    GridPoint2 getCurrentCoordinates();
}
