package ru.test;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.platformer.game.Direction;
import ru.platformer.game.model.*;
import ru.platformer.game.model.actions.move.MoveAction;
import ru.platformer.game.model.objects.Level;
import ru.platformer.game.model.objects.Obstacle;
import ru.platformer.game.model.objects.Tank;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class MoveActionTest {
    @ParameterizedTest
    @EnumSource(Direction.class)
    public void testApplyingMoveActionWithCollisionChangeDestinationCoordinates(Direction direction) {
        CollisionDetector collisionDetector = new CollisionDetector();
        ArrayList<LevelListener> levelListeners = new ArrayList<>();
        levelListeners.add(collisionDetector);
        Level level = new Level(levelListeners, 0, 0);
        level.addGameObject(new Tank(new GridPoint2(0, 0)));
        level.addGameObject(new Tank(new GridPoint2(1, 1)));
        level.addGameObject(new Obstacle(new GridPoint2(2, 0)));
        Tank tank = new Tank(new GridPoint2(0, 0));
        level.addGameObject(tank);

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
        CollisionDetector collisionDetector = new CollisionDetector();
        ArrayList<LevelListener> levelListeners = new ArrayList<>();
        levelListeners.add(collisionDetector);
        Level level = new Level(levelListeners, 0, 0);
        level.addGameObject(new Tank(new GridPoint2(0, 0)));
        level.addGameObject(new Tank(new GridPoint2(1, 1)));
        level.addGameObject(new Obstacle(new GridPoint2(2, 0)));
        level.addGameObject(new Tank(direction.getCoordinates()));
        GridPoint2 tankStartCoordinates = new GridPoint2(0, 0);
        Tank tank = new Tank(tankStartCoordinates);
        level.addGameObject(tank);

        MoveAction moveAction = new MoveAction(direction, collisionDetector, tank);
        moveAction.apply();

        float targetRotation = direction.getRotation();
        assertEquals(tankStartCoordinates, tank.getDestinationCoordinates());
        assertEquals(targetRotation, tank.getRotation());
    }
}