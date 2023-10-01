package ru.mipt.bit.test;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
    void testUpdateStateTankFinishedMovementCollidesCreated() {
        GridPoint2 targetCoordinates = tank.getCurrentCoordinates().add(Direction.UP.getCoordinates());
        tank.moveTo(Direction.UP.getCoordinates(), Direction.UP.getRotation());

        assertFalse(level.collidesExist(targetCoordinates));

        float deltaTime = 1f;
        level.updateState(deltaTime);

        assertTrue(level.collidesExist(targetCoordinates));
    }

    @Test
    void testCollides() {
        assertTrue(level.collidesExist(tank.getCurrentCoordinates()));
        assertTrue(level.collidesExist(obstacle.getCurrentCoordinates()));
        GridPoint2 freeCoordinates = new GridPoint2(2, 2);
        assertFalse(level.collidesExist(freeCoordinates));

    }
}