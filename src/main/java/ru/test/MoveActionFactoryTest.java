package ru.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.platformer.game.Direction;
import ru.platformer.game.model.actions.move.MoveAction;
import ru.platformer.game.model.actions.move.MoveFactory;


class MoveActionFactoryTest {

    @ParameterizedTest
    @EnumSource(Direction.class)
    void testCreateMoveDirection(Direction direction) {
        MoveFactory moveFactoryUp = new MoveFactory(direction, null, 0, 0);

        MoveAction moveActionUp = moveFactoryUp.create(null);

//        Assertions.assertEquals(moveActionUp.getDirection(), direction);
    }
}