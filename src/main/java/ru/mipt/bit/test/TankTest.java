//package ru.mipt.bit.test;
//
//import com.badlogic.gdx.math.GridPoint2;
//import ru.mipt.bit.platformer.action.Direction;
//import ru.mipt.bit.platformer.gameEntities.Tank;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;
//
//class TankTest {
//    Tank tank;
//
//    @BeforeEach
//    void setUp() {
//        tank = new Tank(new GridPoint2(0, 0));
//    }
//    @Test
//    void testTankMoveToNewDirection() {
//        GridPoint2 targetCoordinates = tank.getCurrentCoordinates().add(Direction.UP.getCoordinates());
//        tank.moveTo(Direction.UP.getCoordinates(), Direction.UP.getRotation());
//        assertEquals(targetCoordinates, tank.getDestinationCoordinates());
//        assertEquals(Direction.UP.getRotation(), tank.getRotation());
//    }
//    @Test
//    void testTankFinishedMovement() {
//        float deltaTime = 1f;
//        GridPoint2 targetCoordinates = tank.getCurrentCoordinates().add(Direction.RIGHT.getCoordinates());
//        tank.moveTo(Direction.RIGHT.getCoordinates(), Direction.RIGHT.getRotation());
//        tank.updateState(deltaTime);
//
//        assertEquals(targetCoordinates, tank.getCurrentCoordinates());
//        assertEquals(targetCoordinates, tank.getDestinationCoordinates());
//
//    }
//
//    @Test
//    void testTankMoving() {
//        float deltaTime = 0.1f;
//        GridPoint2 targetCoordinates = tank.getCurrentCoordinates().add(Direction.RIGHT.getCoordinates());
//        tank.moveTo(Direction.RIGHT.getCoordinates(), Direction.RIGHT.getRotation());
//        float movementProgressExpected = continueProgress(tank.getMovementProgress(), deltaTime, Tank.MOVEMENT_SPEED);
//        tank.updateState(deltaTime);
//
//        assertNotEquals(targetCoordinates, tank.getCurrentCoordinates());
//        assertEquals(targetCoordinates, tank.getDestinationCoordinates());
//        assertEquals(movementProgressExpected, tank.getMovementProgress());
//
//
//    }
//}