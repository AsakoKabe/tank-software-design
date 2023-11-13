package ru.platformer.game.model.objects;

import com.badlogic.gdx.math.GridPoint2;
import ru.platformer.game.Direction;
import ru.platformer.game.GameObject;
import ru.platformer.game.model.*;
import ru.platformer.util.GdxGameUtils;

import static com.badlogic.gdx.math.MathUtils.isEqual;

public class Tank implements GameObject, Movable, Shooter, Damaged, Colliding {

    private final Level level;
    private final CollisionDetector collisionDetector;
    private final GridPoint2 currentCoordinates;
    private final int damage;
    float movementProgress = MOVEMENT_COMPLETED;
    private GridPoint2 destinationCoordinates;
    private Direction direction;
    private int healthPoint;


    public Tank(
            GridPoint2 startCoordinates,
            int healthPoint,
            int damage,
            Level level,
            CollisionDetector collisionDetector
    ) {
        currentCoordinates = startCoordinates;
        destinationCoordinates = startCoordinates;
        this.direction = Direction.UP;
        this.healthPoint = healthPoint;
        this.damage = damage;
        this.level = level;
        this.collisionDetector = collisionDetector;
    }

    public void updateState(float deltaTime) {
        if (isDead()){
            level.deleteGameObject(this);
            return;
        }
        updateMovementState(deltaTime);
    }


    private void updateMovementState(float deltaTime) {
        movementProgress = GdxGameUtils.continueProgress(movementProgress, deltaTime,
                MOVEMENT_SPEED);
        if (isEqual(movementProgress, MOVEMENT_COMPLETED)) {
            // record that the player has reached his/her destination
            currentCoordinates.set(destinationCoordinates);
        }
    }

    private boolean isDead() {
        return healthPoint == 0;
    }

    public float getMovementProgress() {
        return movementProgress;
    }

    public GridPoint2 getCurrentCoordinates() {
        return currentCoordinates.cpy();
    }

    @Override
    public boolean isBusyCoordinate(GridPoint2 coordinates) {
        return currentCoordinates.equals(coordinates) || destinationCoordinates.equals(coordinates);
    }

    public GridPoint2 getDestinationCoordinates() {
        return destinationCoordinates.cpy();
    }

    private boolean isNotMoving() {
        return isEqual(movementProgress, 1f);
    }

    @Override
    public void moveToDirection(Direction direction, boolean onlyRotation) {
        if (isNotMoving()) {
            if (!onlyRotation) {
                destinationCoordinates = direction.applyCoordinates(currentCoordinates);
            }
            this.direction = direction;
            movementProgress = MOVEMENT_STARTED;
        }
    }

    public float getRotation() {
        return direction.getRotation();
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public void createBullet() {
        if (isNotMoving()) {
            Bullet bullet = new Bullet(
                    createBulletCoordinates(),
                    direction,
                    damage,
                    0.25f,
                    level,
                    collisionDetector
            );
            level.addGameObject(bullet);
        }
    }

    private GridPoint2 createBulletCoordinates() {
        return currentCoordinates.cpy().add(direction.getCoordinates());
    }

    @Override
    public void takeDamage(int damage) {
        this.healthPoint -= damage;
    }

}
