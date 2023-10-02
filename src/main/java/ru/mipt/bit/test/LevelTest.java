package ru.mipt.bit.test;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.mipt.bit.platformer.action.Direction;
import ru.mipt.bit.platformer.gameEntities.Level;
import ru.mipt.bit.platformer.gameEntities.Obstacle;
import ru.mipt.bit.platformer.gameEntities.Tank;

import static org.junit.jupiter.api.Assertions.*;

class LevelTest {
    Level level;
    Tank tank;
    Obstacle obstacle;
    @BeforeEach
    void setUp() {
        level = new Level();
        tank = new Tank(new GridPoint2(0, 0));
        obstacle = new Obstacle(new GridPoint2(1, 1));
        level.addGameEntity(tank);
        level.addGameEntity(obstacle);

    }

    @Test
    void testUpdateState() {
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
    void testCollides() {
        assertTrue(level.collidesExist(tank.getCurrentCoordinates()));
        assertTrue(level.collidesExist(obstacle.getCurrentCoordinates()));
        GridPoint2 freeCoordinates = new GridPoint2(2, 2);
        assertFalse(level.collidesExist(freeCoordinates));

    }
}