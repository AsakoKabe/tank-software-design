package ru.platformer.game.model;

import com.badlogic.gdx.math.GridPoint2;
import ru.platformer.game.GameObject;

public class Obstacle implements GameObject {
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
}
