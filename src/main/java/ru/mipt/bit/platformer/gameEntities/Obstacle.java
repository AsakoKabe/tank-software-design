package ru.mipt.bit.platformer.gameEntities;

import com.badlogic.gdx.math.GridPoint2;

public class Obstacle implements GameEntity{
    protected GridPoint2 currentCoordinates;


    public Obstacle(
            GridPoint2 startCoordinates
    ) {
        currentCoordinates = startCoordinates;
    }

    public GridPoint2 getCurrentCoordinates() {
        return currentCoordinates;
    }

    @Override
    public void updateState(float deltaTime) {

    }

    @Override
    public void moveToDirection(GridPoint2 coordinates, float rotation) {

    }
}
