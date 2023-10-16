//package ru.test;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.EnumSource;
//import org.mockito.Mockito;
//import ru.platformer.game.Direction;
//import ru.platformer.game.model.actions.move.MoveAction;
//import ru.platformer.game.model.actions.move.MoveFactory;
//import ru.platformer.game.model.Level;
//import ru.platformer.game.model.Tank;
//
//class MoveActionFactoryTest {
//
//    @ParameterizedTest
//    @EnumSource(Direction.class)
//    void testCreateMoveDirection(Direction direction) {
//        Level mockedLevel = Mockito.mock(Level.class);
//        MoveFactory moveFactoryUp = new MoveFactory(direction, null);
//
//        MoveAction moveActionUp = moveFactoryUp.create(Mockito.mock(Tank.class));
//
//        Assertions.assertEquals(moveActionUp.getDirection(), direction);
//    }
//}