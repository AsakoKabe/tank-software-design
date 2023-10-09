package ru.test;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.jupiter.api.Test;
import ru.platformer.game.model.Obstacle;

import static org.junit.jupiter.api.Assertions.*;

class ObstacleTest {

    @Test
    void testStateDoesntChange() {
        Obstacle obstacle = new Obstacle(new GridPoint2(0, 0));
        float deltaTime = 1f;
        GridPoint2 prevCoordinates = obstacle.getCurrentCoordinates();

        obstacle.updateState(deltaTime);

        assertEquals(prevCoordinates, obstacle.getCurrentCoordinates());
    }
}