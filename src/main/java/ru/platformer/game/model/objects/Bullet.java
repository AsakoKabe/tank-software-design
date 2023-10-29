package ru.platformer.game.model.objects;

import com.badlogic.gdx.math.GridPoint2;
import ru.platformer.game.Direction;
import ru.platformer.game.GameObject;
import ru.platformer.game.model.Movable;
import ru.platformer.util.GdxGameUtils;

import static com.badlogic.gdx.math.MathUtils.isEqual;

public class Bullet implements GameObject, Movable {

    float movementProgress;
    private final GridPoint2 currentCoordinates;
    private final Direction direction;
    private final GridPoint2 destinationCoordinates;


    public Bullet(GridPoint2 startCoordinates, Direction direction) {
        this.direction = direction;
        this.currentCoordinates = startCoordinates;
        this.destinationCoordinates = createNewDestinationCoordinates();
        movementProgress = MOVEMENT_STARTED;
    }


    @Override
    public void updateState(float deltaTime) {
        updateCoordinates(deltaTime);
    }

    private void updateCoordinates(float deltaTime) {
        movementProgress = GdxGameUtils.continueProgress(movementProgress, deltaTime, MOVEMENT_SPEED);
        if (finishMovement()) {
            currentCoordinates.set(destinationCoordinates);
//            destinationCoordinates.set(createNewDestinationCoordinates());
//            movementProgress = MOVEMENT_STARTED;
        }
    }

    private boolean finishMovement() {
        return isEqual(movementProgress, MOVEMENT_COMPLETED);
    }

    private GridPoint2 createNewDestinationCoordinates() {
        return currentCoordinates.cpy().add(direction.getCoordinates());
    }

    public float getMovementProgress() {
        return movementProgress;
    }

    public GridPoint2 getDestinationCoordinates() {
        return destinationCoordinates.cpy();
    }

    @Override
    public GridPoint2 getCurrentCoordinates() {
        return this.currentCoordinates.cpy();
    }

}
