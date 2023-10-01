package ru.mipt.bit.test;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mipt.bit.platformer.action.Direction;
import ru.mipt.bit.platformer.action.Move;
import ru.mipt.bit.platformer.gameEntities.Tank;

import static org.junit.jupiter.api.Assertions.*;

class MoveTest {
    Move move;
    Tank tank;

    @BeforeEach
    void setUp() {
        move = new Move(Direction.LEFT.getCoordinates(), Direction.LEFT.getRotation());
        tank = new Tank(new GridPoint2(0, 0));

    }

    @Test
    void testMoveForTank() {
        GridPoint2 targetCoordinates = tank.getCurrentCoordinates().add(move.getCoordinates());
        float targetRotation = move.getRotation();
        move.apply(tank);
        assertEquals(targetCoordinates, tank.getDestinationCoordinates());
        assertEquals(targetRotation, tank.getRotation());
    }

    @Test
    void testMoveForTankWithResetCoordinates() {
        GridPoint2 prevTankCoordinates = tank.getDestinationCoordinates();
        GridPoint2 targetCoordinates = tank.getCurrentCoordinates().add(move.getCoordinates());
        float targetRotation = move.getRotation();

        move.resetCoordinates();
        move.apply(tank);

        assertNotEquals(targetCoordinates, tank.getDestinationCoordinates());
        assertEquals(prevTankCoordinates, tank.getDestinationCoordinates());
        assertEquals(targetRotation, tank.getRotation());
    }

}