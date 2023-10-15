package ru.test;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.platformer.game.Direction;
import ru.platformer.game.model.LevelListener;
import ru.platformer.game.model.actions.move.MoveAction;
import ru.platformer.game.model.Level;
import ru.platformer.game.model.Obstacle;
import ru.platformer.game.model.Tank;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MoveActionTest {
    @ParameterizedTest
    @EnumSource(Direction.class)
    public void testApplyingMoveActionWithCollisionChangeDestinationCoordinates(Direction direction) {
        ArrayList<LevelListener> levelListeners = new ArrayList<>();
        Level level = new Level(levelListeners);
        level.addGameObject(new Tank(new GridPoint2(0, 0)));
        level.addGameObject(new Tank(new GridPoint2(1, 1)));
        level.addGameObject(new Obstacle(new GridPoint2(2, 0)));
        Tank tank = new Tank(new GridPoint2(0, 0));
        level.addGameObject(tank);
        MoveAction moveAction = new MoveAction(direction, level, tank);

        moveAction.apply();

        GridPoint2 targetCoordinates = direction.applyCoordinates(tank.getCurrentCoordinates());
        float targetRotation = direction.getRotation();
        assertEquals(targetCoordinates, tank.getDestinationCoordinates());
        assertEquals(targetRotation, tank.getRotation());
    }

    @ParameterizedTest
    @EnumSource(Direction.class)
    public void testApplyingMoveActionWithCollisionByDirection(Direction direction) {
        ArrayList<LevelListener> levelListeners = new ArrayList<>();
        Level level = new Level(levelListeners);
        level.addGameObject(new Tank(new GridPoint2(0, 0)));
        level.addGameObject(new Tank(new GridPoint2(1, 1)));
        level.addGameObject(new Obstacle(new GridPoint2(2, 0)));
        level.addGameObject(new Tank(direction.getCoordinates()));
        GridPoint2 tankStartCoordinates = new GridPoint2(0, 0);
        Tank tank = new Tank(tankStartCoordinates);
        level.addGameObject(tank);
        MoveAction moveAction = new MoveAction(direction, level, tank);

        moveAction.apply();

        float targetRotation = direction.getRotation();
        assertEquals(tankStartCoordinates, tank.getDestinationCoordinates());
        assertEquals(targetRotation, tank.getRotation());
    }
}