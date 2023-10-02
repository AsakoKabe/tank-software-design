package ru.mipt.bit.test;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import ru.mipt.bit.platformer.action.Direction;
import ru.mipt.bit.platformer.gameEntities.Tank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;

class TankTest {
    Tank tank;

    @BeforeEach
    void setUp() {
        tank = new Tank(new GridPoint2(0, 0));
    }
    @ParameterizedTest
    @EnumSource(Direction.class)
    void testTankMoveToNewDirection(Direction direction) {
        GridPoint2 targetCoordinates = direction.applyCoordinates(tank.getCurrentCoordinates());
        tank.moveToDirection(direction, false);
        assertEquals(targetCoordinates, tank.getDestinationCoordinates());

        tank.moveToDirection(direction, true);
        assertEquals(targetCoordinates, tank.getDestinationCoordinates());
    }

    @ParameterizedTest
    @EnumSource(Direction.class)
    void testTankFinishedMovement(Direction direction) {
        float deltaTime = 1f;
        GridPoint2 targetCoordinates = direction.applyCoordinates(tank.getCurrentCoordinates());
        tank.moveToDirection(direction, false);
        tank.updateState(deltaTime);

        assertEquals(targetCoordinates, tank.getCurrentCoordinates());
        assertEquals(targetCoordinates, tank.getDestinationCoordinates());

    }
    @ParameterizedTest
    @EnumSource(Direction.class)
    void testTankMoving(Direction direction) {
        float deltaTime = 0.1f;
        GridPoint2 targetCoordinates = direction.applyCoordinates(tank.getCurrentCoordinates());
        tank.moveToDirection(direction, false);
        float movementProgressExpected = continueProgress(tank.getMovementProgress(), deltaTime, Tank.MOVEMENT_SPEED);
        tank.updateState(deltaTime);

        assertNotEquals(targetCoordinates, tank.getCurrentCoordinates());
        assertEquals(targetCoordinates, tank.getDestinationCoordinates());
        assertEquals(movementProgressExpected, tank.getMovementProgress());


    }

}