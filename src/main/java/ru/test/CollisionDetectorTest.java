package ru.test;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.jupiter.api.Test;
import ru.platformer.game.model.*;
import ru.platformer.game.model.objects.Level;
import ru.platformer.game.model.objects.Obstacle;
import ru.platformer.game.model.objects.Tank;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CollisionDetectorTest {

    @Test
    void collisionExistForAlreadyExistGameObject() {
        CollisionDetector collisionDetector = new CollisionDetector();
        ArrayList<LevelListener> levelListeners = new ArrayList<>();
        levelListeners.add(collisionDetector);
        Level level = new Level(levelListeners, 0, 0);
        GridPoint2 tank1Coordinates = new GridPoint2(0, 0);
        Tank tank1 = new Tank(tank1Coordinates, 0, 0);
        GridPoint2 obstacleCoordinates = new GridPoint2(1, 1);
        Obstacle obstacle1 = new Obstacle(obstacleCoordinates);

        level.addGameObject(tank1);
        level.addGameObject(obstacle1);


        assertTrue(collisionDetector.collisionExist(tank1Coordinates));
        assertTrue(collisionDetector.collisionExist(obstacleCoordinates));
    }

}