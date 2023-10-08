package ru.mipt.bit.test;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import ru.mipt.bit.platformer.action.Direction;
import ru.mipt.bit.platformer.action.MoveAction;
import ru.mipt.bit.platformer.action.MoveFactory;
import ru.mipt.bit.platformer.gameEntities.GameEntity;
import ru.mipt.bit.platformer.gameEntities.Level;
import ru.mipt.bit.platformer.gameEntities.Tank;

import static org.junit.jupiter.api.Assertions.*;

class MoveActionFactoryTest {

    @ParameterizedTest
    @EnumSource(Direction.class)
    void testCreateMoveDirection(Direction direction) {
        Level mockedLevel = Mockito.mock(Level.class);
        MoveFactory moveFactoryUp = new MoveFactory(direction, mockedLevel);

        MoveAction moveActionUp = moveFactoryUp.create(Mockito.mock(Tank.class));

        assertEquals(moveActionUp.getDirection(), direction);
    }
}