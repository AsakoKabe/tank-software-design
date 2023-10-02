//package ru.mipt.bit.test;
//
//import org.junit.jupiter.api.Test;
//import ru.mipt.bit.platformer.action.Direction;
//import ru.mipt.bit.platformer.action.MoveAction;
//import ru.mipt.bit.platformer.action.MoveFactory;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class MoveActionFactoryTest {
//
//    @Test
//    void testCreateMoveDirection() {
//        for (Direction direction: Direction.values()){
//            MoveFactory moveFactoryUp = new MoveFactory(direction);
//            MoveAction moveActionUp = moveFactoryUp.create();
//
//            assertEquals(moveActionUp.getRotation(), direction.getRotation());
//            assertEquals(moveActionUp.getCoordinates(), direction.getCoordinates());
//        }
//    }
//}