package ru.mipt.bit.test;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.mipt.bit.platformer.action.Direction;
import ru.mipt.bit.platformer.action.MoveAction;
import ru.mipt.bit.platformer.gameEntities.Level;
import ru.mipt.bit.platformer.gameEntities.Obstacle;
import ru.mipt.bit.platformer.gameEntities.Tank;

import static org.junit.jupiter.api.Assertions.*;

public class MoveActionTest {
    private Tank tank;
    private Level level;

    @BeforeEach
    void setUp() {
        level = new Level();
        tank = new Tank(new GridPoint2(0, 0));
        level.addGameEntity(tank);
        level.addGameEntity(new Tank(new GridPoint2(0, 0)));
        level.addGameEntity(new Tank(new GridPoint2(1, 1)));
        level.addGameEntity(new Obstacle(new GridPoint2(2, 0)));
    }

    @ParameterizedTest
    @EnumSource(Direction.class)
    public void testMoveForTankWithOutCollision(Direction direction) {
        MoveAction moveAction = new MoveAction(direction, level, tank);
        GridPoint2 targetCoordinates = direction.applyCoordinates(tank.getCurrentCoordinates());
        float targetRotation = direction.getRotation();
        moveAction.apply();

        assertEquals(targetCoordinates, tank.getDestinationCoordinates());
        assertEquals(targetRotation, tank.getRotation());
    }

    @Test
    public void testMoveForTankWithCollision() {
        Direction direction = Direction.RIGHT;
        level.addGameEntity(new Tank(new GridPoint2(1, 0)));

        MoveAction moveAction = new MoveAction(direction, level, tank);
        GridPoint2 targetCoordinates = direction.applyCoordinates(tank.getCurrentCoordinates());
        float targetRotation = direction.getRotation();
        moveAction.apply();

        assertNotEquals(targetCoordinates, tank.getDestinationCoordinates());
        assertEquals(targetRotation, tank.getRotation());
    }
}