package ru.test;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.platformer.game.Direction;
import ru.platformer.game.model.*;
import ru.platformer.game.model.actions.move.MoveAction;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class MoveActionTest {
    @ParameterizedTest
    @EnumSource(Direction.class)
    public void testApplyingMoveActionWithCollisionChangeDestinationCoordinates(Direction direction) {
        Level level = new Level(mock(ArrayList.class));
        level.addGameObject(new Tank(new GridPoint2(0, 0)));
        level.addGameObject(new Tank(new GridPoint2(1, 1)));
        level.addGameObject(new Obstacle(new GridPoint2(2, 0)));
        Tank tank = new Tank(new GridPoint2(0, 0));
        level.addGameObject(tank);
        CollisionDetector collisionDetector = new CollisionDetector(level);
        MoveAction moveAction = new MoveAction(direction, collisionDetector, tank);

        moveAction.apply();

        GridPoint2 targetCoordinates = direction.applyCoordinates(tank.getCurrentCoordinates());
        float targetRotation = direction.getRotation();
        assertEquals(targetCoordinates, tank.getDestinationCoordinates());
        assertEquals(targetRotation, tank.getRotation());
    }

    @ParameterizedTest
    @EnumSource(Direction.class)
    public void testApplyingMoveActionWithCollisionByDirection(Direction direction) {
        Level level = new Level(mock(ArrayList.class));
        level.addGameObject(new Tank(new GridPoint2(0, 0)));
        level.addGameObject(new Tank(new GridPoint2(1, 1)));
        level.addGameObject(new Obstacle(new GridPoint2(2, 0)));
        level.addGameObject(new Tank(direction.getCoordinates()));
        GridPoint2 tankStartCoordinates = new GridPoint2(0, 0);
        Tank tank = new Tank(tankStartCoordinates);
        level.addGameObject(tank);
        CollisionDetector collisionDetector = new CollisionDetector(level);
        MoveAction moveAction = new MoveAction(direction, collisionDetector, tank);

        moveAction.apply();

        float targetRotation = direction.getRotation();
        assertEquals(tankStartCoordinates, tank.getDestinationCoordinates());
        assertEquals(targetRotation, tank.getRotation());
    }
}