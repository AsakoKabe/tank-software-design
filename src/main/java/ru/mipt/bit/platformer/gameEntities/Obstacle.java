package ru.mipt.bit.platformer.gameEntities;

import com.badlogic.gdx.math.GridPoint2;

public class Obstacle implements GameEntity{
    protected GridPoint2 currentCoordinates;
    float rotation = 0f;


    public Obstacle(
            GridPoint2 startCoordinates
    ) {
        currentCoordinates = startCoordinates;
    }

    public GridPoint2 getCurrentCoordinates() {
        return currentCoordinates;
    }

    public float getRotation() {
        return rotation;
    }
}
