package ru.platformer.game.model.objects;

import com.badlogic.gdx.math.GridPoint2;
import ru.platformer.game.Direction;
import ru.platformer.game.GameObject;
import ru.platformer.game.model.Movable;
import ru.platformer.util.GdxGameUtils;

import static com.badlogic.gdx.math.MathUtils.isEqual;

public class Bullet implements GameObject, Movable {

    private final int damage;
    float movementProgress = MOVEMENT_STARTED;
    private final GridPoint2 currentCoordinates;
    private Direction direction;
    private GridPoint2 destinationCoordinates;


    public Bullet(GridPoint2 startCoordinates, Direction direction, int damage) {
        this.direction = direction;
        this.currentCoordinates = startCoordinates;
        this.destinationCoordinates = direction.applyCoordinates(currentCoordinates);
        this.damage = damage;
    }


    @Override
    public void moveToDirection(Direction direction, boolean onlyRotation) {
        if (!isMoving()){
            destinationCoordinates = direction.applyCoordinates(currentCoordinates);
            this.direction = direction;
            movementProgress = MOVEMENT_STARTED;
        }
    }

    private boolean isMoving() {
        return !isEqual(movementProgress, 1f);
    }
    @Override
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

    @Override
    public GridPoint2 getDestinationCoordinates() {
        return destinationCoordinates;
    }

    @Override
    public GridPoint2 getCurrentCoordinates() {
        return currentCoordinates;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    public float getMovementProgress(){
        return movementProgress;
    }

    public float getRotation() {
        return direction.getRotation();
    }
    public int getDamage() {
        return damage;
    }

}
