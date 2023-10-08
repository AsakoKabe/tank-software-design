package ru.mipt.bit.test;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.mipt.bit.platformer.gameEntities.Level;
import ru.mipt.bit.platformer.gameEntities.Obstacle;
import ru.mipt.bit.platformer.gameEntities.Tank;

import static org.junit.jupiter.api.Assertions.*;

class LevelTest {

    @Test
    void testUpdateState() {
        Level level = new Level();
        Tank mockedTank = Mockito.mock(Tank.class);
        Obstacle mockedObstacle = Mockito.mock(Obstacle.class);
        level.addGameEntity(mockedTank);
        level.addGameEntity(mockedObstacle);
        float deltaTime = 1f;

        level.updateState(deltaTime);

        Mockito.verify(mockedObstacle, Mockito.times(1)).updateState(deltaTime);
        Mockito.verify(mockedTank, Mockito.times(1)).updateState(deltaTime);
    }

    @Test
    void testCollisionExist() {
        Level level = new Level();
        Tank tank = new Tank(new GridPoint2(0, 0));
        Obstacle obstacle = new Obstacle(new GridPoint2(1, 1));

        level.addGameEntity(tank);
        level.addGameEntity(obstacle);
        GridPoint2 freeCoordinates = new GridPoint2(2, 2);

        assertTrue(level.collisionExist(tank.getCurrentCoordinates()));
        assertTrue(level.collisionExist(obstacle.getCurrentCoordinates()));
        assertFalse(level.collisionExist(freeCoordinates));

    }
}