package ru.test;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.platformer.game.Direction;
import ru.platformer.game.model.objects.Tank;

import static org.junit.jupiter.api.Assertions.*;
import static ru.platformer.util.GdxGameUtils.continueProgress;

class TankTest {

    public static final float SPEED = 0.5f;

    @ParameterizedTest
    @EnumSource(Direction.class)
    void testTankMoveToNewDirectionWithCoordinates(Direction direction) {
        Tank tank = new Tank(new GridPoint2(0, 0), SPEED, 0, 0, null, null);

        tank.moveTo(direction);

        GridPoint2 targetCoordinates = direction.applyCoordinates(tank.getCurrentCoordinates());
        assertEquals(targetCoordinates, tank.getDestinationCoordinates());
        assertEquals(direction.getRotation(), tank.getRotation());
    }

    @ParameterizedTest
    @EnumSource(Direction.class)
    void testTankMoveToNewDirectionWithOutCoordinates(Direction direction) {
        GridPoint2 startCoordinates = new GridPoint2(0, 0);
        Tank tank = new Tank(startCoordinates, SPEED, 0, 0, null, null);

        tank.moveTo(direction);

        assertEquals(startCoordinates, tank.getDestinationCoordinates());
        assertEquals(direction.getRotation(), tank.getRotation());
    }

    @ParameterizedTest
    @EnumSource(Direction.class)
    void testTankFinishedMovement(Direction direction) {
        Tank tank = new Tank(new GridPoint2(0, 0), SPEED, 0, 0, null, null);
        float deltaTime = 1f;
        GridPoint2 targetCoordinates = direction.applyCoordinates(tank.getCurrentCoordinates());

        tank.moveTo(direction);
        tank.updateState(deltaTime);

        assertEquals(targetCoordinates, tank.getCurrentCoordinates());
        assertEquals(targetCoordinates, tank.getDestinationCoordinates());

    }
    @ParameterizedTest
    @EnumSource(Direction.class)
    void testTankIsMoving(Direction direction) {
        Tank tank = new Tank(new GridPoint2(0, 0), SPEED, 0, 0, null, null);
        float deltaTime = 0.1f;

        tank.moveTo(direction);
        float movementProgressExpected = continueProgress(tank.getMovementProgress(), deltaTime,
                0.5f);
        tank.updateState(deltaTime);

        GridPoint2 targetCoordinates = direction.applyCoordinates(tank.getCurrentCoordinates());
        assertNotEquals(targetCoordinates, tank.getCurrentCoordinates());
        assertEquals(targetCoordinates, tank.getDestinationCoordinates());
        assertEquals(movementProgressExpected, tank.getMovementProgress());
    }

}