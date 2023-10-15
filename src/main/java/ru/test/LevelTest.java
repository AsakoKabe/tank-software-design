package ru.test;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.platformer.game.model.Level;
import ru.platformer.game.model.LevelListener;
import ru.platformer.game.model.Obstacle;
import ru.platformer.game.model.Tank;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LevelTest {

    @Test
    void testUpdateState() {
        ArrayList<LevelListener> levelListeners = new ArrayList<>();
        Level level = new Level(levelListeners);
        Tank mockedTank = Mockito.mock(Tank.class);
        Obstacle mockedObstacle = Mockito.mock(Obstacle.class);
        level.addGameObject(mockedTank);
        level.addGameObject(mockedObstacle);
        float deltaTime = 1f;

        level.updateState(deltaTime);

        Mockito.verify(mockedObstacle, Mockito.times(1)).updateState(deltaTime);
        Mockito.verify(mockedTank, Mockito.times(1)).updateState(deltaTime);
    }

    @Test
    void testCollisionExist() {
        ArrayList<LevelListener> levelListeners = new ArrayList<>();
        Level level = new Level(levelListeners);
        Tank tank = new Tank(new GridPoint2(0, 0));
        Obstacle obstacle = new Obstacle(new GridPoint2(1, 1));

        level.addGameObject(tank);
        level.addGameObject(obstacle);
        GridPoint2 freeCoordinates = new GridPoint2(2, 2);

        assertTrue(level.collisionExist(tank.getCurrentCoordinates()));
        assertTrue(level.collisionExist(obstacle.getCurrentCoordinates()));
        assertFalse(level.collisionExist(freeCoordinates));

    }
}