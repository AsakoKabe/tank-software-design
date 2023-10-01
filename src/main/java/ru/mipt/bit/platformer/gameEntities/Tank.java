package ru.mipt.bit.platformer.gameEntities;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.gameEntities.GameEntity;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;

public class Tank implements GameEntity {

    public static final float MOVEMENT_SPEED = 0.4f;
    private static final float MOVEMENT_COMPLETED = 1f;
    private static final int MOVEMENT_STARTED = 0;

    float movementProgress = MOVEMENT_COMPLETED;
    float rotation = 0f;
    private GridPoint2 destinationCoordinates;
    private final GridPoint2 currentCoordinates;

    public Tank(
            GridPoint2 startCoordinates
    ) {
        currentCoordinates = startCoordinates;
        destinationCoordinates = startCoordinates;
    }

    public void updateState(float deltaTime){
        updateMovementState(deltaTime);
    }

    private void updateMovementState(float deltaTime) {
        movementProgress = continueProgress(movementProgress, deltaTime, MOVEMENT_SPEED);
        if (isEqual(movementProgress, MOVEMENT_COMPLETED)) {
            // record that the player has reached his/her destination
            currentCoordinates.set(destinationCoordinates);
        }
    }

    public float getMovementProgress() {
        return movementProgress;
    }

    public GridPoint2 getCurrentCoordinates() {
        return currentCoordinates.cpy();
    }

    public GridPoint2 getDestinationCoordinates() {
        return destinationCoordinates.cpy();
    }

    private boolean isMoving() {
        return !isEqual(movementProgress, 1f);
    }

    public float getRotation() {
        return rotation;
    }

    @Override
    public void moveTo(GridPoint2 coordinates, float rotation) {
        if (!isMoving()){
            destinationCoordinates = currentCoordinates.cpy().add(coordinates);
            this.rotation = rotation;
            movementProgress = MOVEMENT_STARTED;
        }
    }
}
