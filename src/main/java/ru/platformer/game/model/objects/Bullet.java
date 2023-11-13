package ru.platformer.game.model.objects;

import com.badlogic.gdx.math.GridPoint2;
import ru.platformer.game.Direction;
import ru.platformer.game.GameObject;
import ru.platformer.game.model.Colliding;
import ru.platformer.game.model.CollisionDetector;
import ru.platformer.game.model.Damaged;
import ru.platformer.game.model.Explosion;
import ru.platformer.util.GdxGameUtils;

import static com.badlogic.gdx.math.MathUtils.isEqual;

public class Bullet implements GameObject, Colliding {

    private static final float MOVEMENT_STARTED = 0.f;
    private static final float MOVEMENT_COMPLETED = 1.f;
    private final int damage;
    private final GridPoint2 currentCoordinates;
    private final Level level;
    private final CollisionDetector collisionDetector;
    private final float movementSpeed;
    float movementProgress = MOVEMENT_STARTED;
    private final Direction direction;
    private GridPoint2 destinationCoordinates;


    public Bullet(
            GridPoint2 startCoordinates,
            Direction direction,
            int damage,
            float movementSpeed,
            Level level,
            CollisionDetector collisionDetector
    ) {
        this.direction = direction;
        this.currentCoordinates = startCoordinates;
        this.destinationCoordinates = direction.applyCoordinates(currentCoordinates);
        this.damage = damage;
        this.movementSpeed = movementSpeed;
        this.level = level;
        this.collisionDetector = collisionDetector;
    }

    @Override
    public void updateState(float deltaTime) {
        hit();
        moveTo();
        updateMovementState(deltaTime);
    }

    private void hit() {
        if (isHit()){
            level.deleteGameObject(this);
            dealDamage();
        }
    }

    private void dealDamage() {
        Explosion explosion = new Explosion(currentCoordinates, level, 0.5f);
        level.addGameObject(explosion);

        Colliding collisionObject =
                collisionDetector.getObjectByCoordinates(currentCoordinates);
        if (collisionObject instanceof Damaged){
            ((Damaged) collisionObject).takeDamage(damage);
        }
    }

    private boolean isHit() {
        return !collisionDetector.getObjectByCoordinates(currentCoordinates).equals(this);
    }

    private void moveTo() {
        if (!isMoving()) {
            destinationCoordinates = direction.applyCoordinates(currentCoordinates);
            movementProgress = 0;
        }
    }

    private boolean isMoving() {
        return !isEqual(movementProgress, 1f);
    }


    private void updateMovementState(float deltaTime) {
        movementProgress = GdxGameUtils.continueProgress(
                movementProgress,
                deltaTime,
                movementSpeed
        );
        if (isEqual(movementProgress, MOVEMENT_COMPLETED)) {
            // record that the player has reached his/her destination
            currentCoordinates.set(destinationCoordinates);
        }
    }

    @Override
    public GridPoint2 getCurrentCoordinates() {
        return currentCoordinates;
    }

    public float getMovementProgress() {
        return movementProgress;
    }

    public float getRotation() {
        return direction.getRotation();
    }

    public GridPoint2 getDestinationCoordinates() {
        return destinationCoordinates;
    }

    @Override
    public boolean isBusyCoordinate(GridPoint2 coordinates) {
        return currentCoordinates.equals(coordinates) || destinationCoordinates.equals(coordinates);
    }
}
