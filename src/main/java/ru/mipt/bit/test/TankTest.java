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

    @ParameterizedTest
    @EnumSource(Direction.class)
    void testTankMoveToNewDirectionWithCoordinates(Direction direction) {
        Tank tank = new Tank(new GridPoint2(0, 0));

        tank.moveToDirection(direction, false);

        GridPoint2 targetCoordinates = direction.applyCoordinates(tank.getCurrentCoordinates());
        assertEquals(targetCoordinates, tank.getDestinationCoordinates());
        assertEquals(direction.getRotation(), tank.getRotation());
    }

    @ParameterizedTest
    @EnumSource(Direction.class)
    void testTankMoveToNewDirectionWithOutCoordinates(Direction direction) {
        GridPoint2 startCoordinates = new GridPoint2(0, 0);
        Tank tank = new Tank(startCoordinates);

        tank.moveToDirection(direction, true);

        assertEquals(startCoordinates, tank.getDestinationCoordinates());
        assertEquals(direction.getRotation(), tank.getRotation());
    }

    @ParameterizedTest
    @EnumSource(Direction.class)
    void testTankFinishedMovement(Direction direction) {
        Tank tank = new Tank(new GridPoint2(0, 0));
        float deltaTime = 1f;
        GridPoint2 targetCoordinates = direction.applyCoordinates(tank.getCurrentCoordinates());

        tank.moveToDirection(direction, false);
        tank.updateState(deltaTime);

        assertEquals(targetCoordinates, tank.getCurrentCoordinates());
        assertEquals(targetCoordinates, tank.getDestinationCoordinates());

    }
    @ParameterizedTest
    @EnumSource(Direction.class)
    void testTankIsMoving(Direction direction) {
        Tank tank = new Tank(new GridPoint2(0, 0));
        float deltaTime = 0.1f;

        tank.moveToDirection(direction, false);
        float movementProgressExpected = continueProgress(tank.getMovementProgress(), deltaTime, Tank.MOVEMENT_SPEED);
        tank.updateState(deltaTime);

        GridPoint2 targetCoordinates = direction.applyCoordinates(tank.getCurrentCoordinates());
        assertNotEquals(targetCoordinates, tank.getCurrentCoordinates());
        assertEquals(targetCoordinates, tank.getDestinationCoordinates());
        assertEquals(movementProgressExpected, tank.getMovementProgress());
    }

}