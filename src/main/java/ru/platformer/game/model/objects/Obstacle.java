package ru.platformer.game.model.objects;

import com.badlogic.gdx.math.GridPoint2;
import ru.platformer.game.GameObject;
import ru.platformer.game.model.Colliding;

public class Obstacle implements GameObject, Colliding {
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
    public boolean isBusyCoordinate(GridPoint2 coordinates) {
        return currentCoordinates.equals(coordinates);
    }

    @Override
    public void updateState(float deltaTime) {
    }
}
