//package ru.mipt.bit.test;
//
//import com.badlogic.gdx.math.GridPoint2;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import ru.mipt.bit.platformer.action.Direction;
//import ru.mipt.bit.platformer.action.MoveAction;
//import ru.mipt.bit.platformer.gameEntities.Tank;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class MoveActionTest {
//    MoveAction moveAction;
//    Tank tank;
//
//    @BeforeEach
//    void setUp() {
//        moveAction = new MoveAction(Direction.LEFT.getCoordinates(), Direction.LEFT.getRotation());
//        tank = new Tank(new GridPoint2(0, 0));
//
//    }
//
//    @Test
//    void testMoveForTank() {
//        GridPoint2 targetCoordinates = tank.getCurrentCoordinates().add(moveAction.getCoordinates());
//        float targetRotation = moveAction.getRotation();
//        moveAction.apply(tank);
//        assertEquals(targetCoordinates, tank.getDestinationCoordinates());
//        assertEquals(targetRotation, tank.getRotation());
//    }
//
//    @Test
//    void testMoveForTankWithResetCoordinates() {
//        GridPoint2 prevTankCoordinates = tank.getDestinationCoordinates();
//        GridPoint2 targetCoordinates = tank.getCurrentCoordinates().add(moveAction.getCoordinates());
//        float targetRotation = moveAction.getRotation();
//
//        moveAction.resetCoordinates();
//        moveAction.apply(tank);
//
//        assertNotEquals(targetCoordinates, tank.getDestinationCoordinates());
//        assertEquals(prevTankCoordinates, tank.getDestinationCoordinates());
//        assertEquals(targetRotation, tank.getRotation());
//    }
//
//}