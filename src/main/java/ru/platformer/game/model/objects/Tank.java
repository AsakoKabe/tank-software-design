package ru.platformer.game.model.objects;

import com.badlogic.gdx.math.GridPoint2;
import ru.platformer.game.Direction;
import ru.platformer.game.GameObject;
import ru.platformer.game.model.*;
import ru.platformer.game.model.objects.tankStates.HeavyTank;
import ru.platformer.game.model.objects.tankStates.LightTank;
import ru.platformer.game.model.objects.tankStates.MediumTank;
import ru.platformer.util.GdxGameUtils;

import java.util.HashMap;
import java.util.Map;

import static com.badlogic.gdx.math.MathUtils.isEqual;

public class Tank implements GameObject, Movable, Shooter, Damaged, Colliding, Health {

    private final Level level;
    private final CollisionDetector collisionDetector;
    private final GridPoint2 currentCoordinates;
    private final int damage;
    private final float speed;
    private TankState state;
    private final float maxHealthPoint;
    float movementProgress = MOVEMENT_COMPLETED;
    private GridPoint2 destinationCoordinates;
    private Direction direction;
    private float healthPoint;


    public Tank(
            GridPoint2 startCoordinates,
            float speed,
            int healthPoint,
            int damage,
            Level level,
            CollisionDetector collisionDetector
    ) {
        currentCoordinates = startCoordinates;
        destinationCoordinates = startCoordinates;
        this.direction = Direction.UP;
        this.healthPoint = healthPoint;
        this.maxHealthPoint = healthPoint;
        this.damage = damage;
        this.level = level;
        this.collisionDetector = collisionDetector;
        this.speed = speed;
        this.state = new LightTank(level, this, collisionDetector);
    }


    @Override
    public void updateState(float deltaTime) {
        if (isDead()) {
            level.deleteGameObject(this);
            return;
        }
        state.continueMovement(deltaTime);
    }

    @Override
    public boolean isBusyCoordinate(GridPoint2 coordinates) {
        return currentCoordinates.equals(coordinates) || destinationCoordinates.equals(coordinates);
    }


    @Override
    public void moveTo(Direction direction) {
        if (isNotMoving()) {
            destinationCoordinates = direction.applyCoordinates(currentCoordinates);
        }
    }

    @Override
    public void rotate(Direction direction) {
        if (isNotMoving()) {
            this.direction = direction;
            resetMovementProgress();
        }
    }

    public boolean isNotMoving() {
        return isEqual(movementProgress, MOVEMENT_COMPLETED);
    }
    public void computeMovementProgress(float deltaTime, float speed) {
       movementProgress = GdxGameUtils.continueProgress(
                movementProgress, deltaTime, speed
        );
    }

    public void moveToDestinationCoordinates() {
        currentCoordinates.set(destinationCoordinates);
    }

    public void resetMovementProgress(){
        movementProgress = MOVEMENT_STARTED;
    }

    @Override
    public void shoot() {
        state.shoot();
    }

    @Override
    public void takeDamage(int damage) {
        this.healthPoint -= damage;
        changeStateByHealth();
    }

    private void changeStateByHealth() {
        float proportion = healthPoint / maxHealthPoint;
        if (proportion >= 0.7){
            state = new LightTank(level, this, collisionDetector);
        } else if ((0.15 <= proportion) && (proportion < 0.7)){
            state = new MediumTank(level, this, collisionDetector);
        }
        else {
            state = new HeavyTank(this);
        }
    }

    private boolean isDead() {
        return healthPoint == 0;
    }


    @Override
    public float getHealth() {
        return healthPoint;
    }

    public float getRotation() {
        return direction.getRotation();
    }

    public Direction getDirection() {
        return direction;
    }

    public GridPoint2 getDestinationCoordinates() {
        return destinationCoordinates.cpy();
    }

    public float getMovementProgress() {
        return movementProgress;
    }
    @Override
    public GridPoint2 getCurrentCoordinates() {
        return currentCoordinates;
    }

    public float getSpeed() {
        return speed;
    }

    public int getDamage() {
        return damage;
    }
}
