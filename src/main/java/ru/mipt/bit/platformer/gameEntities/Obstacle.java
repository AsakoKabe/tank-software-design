package ru.mipt.bit.platformer.gameEntities;

import com.badlogic.gdx.math.GridPoint2;

public class Obstacle implements GameEntity{
    private final GridPoint2 currentCoordinates;


    public Obstacle(
            GridPoint2 startCoordinates
    ) {
        currentCoordinates = startCoordinates;
    }

    public GridPoint2 getCurrentCoordinates() {
        return currentCoordinates.cpy();
    }

    @Override
    public void updateState(float deltaTime) {
    }

    @Override
    public void moveTo(GridPoint2 coordinates, float rotation) {
    }
}
