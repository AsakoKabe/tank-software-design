package ru.mipt.bit.platformer.gameEntities;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.action.Direction;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;

public class Tank implements GameEntity, Movable {

    public static final float MOVEMENT_SPEED = 0.4f;
    private static final float MOVEMENT_COMPLETED = 1f;
    private static final int MOVEMENT_STARTED = 0;

    float movementProgress = MOVEMENT_COMPLETED;
    private GridPoint2 destinationCoordinates;
    private final GridPoint2 currentCoordinates;
    private float rotation;

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

    @Override
    public void moveToDirection(Direction direction, Boolean onlyRotation) {
        if (!isMoving()){
            destinationCoordinates = currentCoordinates.cpy().add(direction.getCoordinates());
            this.rotation = direction.getRotation();
            movementProgress = MOVEMENT_STARTED;
        }
    }

    public float getRotation(){
        return rotation;
    }
}
