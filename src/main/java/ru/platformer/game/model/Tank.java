package ru.platformer.game.model;

import com.badlogic.gdx.math.GridPoint2;
import ru.platformer.game.Direction;
import ru.platformer.game.GameObject;
import ru.platformer.util.GdxGameUtils;

import static com.badlogic.gdx.math.MathUtils.isEqual;

public class Tank implements GameObject, Movable {

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
        movementProgress = GdxGameUtils.continueProgress(movementProgress, deltaTime, MOVEMENT_SPEED);
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
    public void moveToDirection(Direction direction, Boolean notUseCoordinates) {
        if (!isMoving()){
            if (!notUseCoordinates){
                destinationCoordinates = direction.applyCoordinates(currentCoordinates);
            }
            this.rotation = direction.getRotation();
            movementProgress = MOVEMENT_STARTED;
        }
    }

    public float getRotation(){
        return rotation;
    }
}
