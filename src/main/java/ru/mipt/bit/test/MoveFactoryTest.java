package ru.mipt.bit.test;

import org.junit.jupiter.api.Test;
import ru.mipt.bit.platformer.action.Direction;
import ru.mipt.bit.platformer.action.Move;
import ru.mipt.bit.platformer.action.MoveFactory;

import static org.junit.jupiter.api.Assertions.*;

class MoveFactoryTest {

    @Test
    void testCreateMoveDirection() {
        for (Direction direction: Direction.values()){
            MoveFactory moveFactoryUp = new MoveFactory(direction);
            Move moveUp = moveFactoryUp.create();

            assertEquals(moveUp.getRotation(), direction.getRotation());
            assertEquals(moveUp.getCoordinates(), direction.getCoordinates());
        }
    }
}