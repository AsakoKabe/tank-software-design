package ru.platformer.game.model.objects;

import com.badlogic.gdx.math.GridPoint2;
import ru.platformer.game.Direction;
import ru.platformer.game.GameObject;
import ru.platformer.game.model.Movable;
import ru.platformer.game.model.Shooter;
import ru.platformer.util.GdxGameUtils;

import static com.badlogic.gdx.math.MathUtils.isEqual;

public class Tank implements GameObject, Movable, Shooter {

    private boolean isShooting;
    float movementProgress = MOVEMENT_COMPLETED;
    private GridPoint2 destinationCoordinates;
    private final GridPoint2 currentCoordinates;
    private Direction direction;


    public Tank(
            GridPoint2 startCoordinates
    ) {
        currentCoordinates = startCoordinates;
        destinationCoordinates = startCoordinates;
        this.direction = Direction.UP;
        this.isShooting = false;
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
        return isEqual(movementProgress, 1f);
    }

    @Override
    public void moveToDirection(Direction direction, boolean onlyRotation) {
        if (isMoving()){
            if (!onlyRotation){
                destinationCoordinates = direction.applyCoordinates(currentCoordinates);
            }
            this.direction = direction;
            movementProgress = MOVEMENT_STARTED;
        }
    }

    public float getRotation(){
        return direction.getRotation();
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public Bullet createBullet() {
        if (!isShooting && isMoving()){
            GridPoint2 bulletCoordinates = createBulletCoordinates();
            return new Bullet(bulletCoordinates, direction);
        }
        return null;
    }

    private GridPoint2 createBulletCoordinates() {
        return currentCoordinates.cpy().add(direction.getCoordinates());
    }
}
