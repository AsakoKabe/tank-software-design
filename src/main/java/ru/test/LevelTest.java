package ru.test;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.platformer.game.model.Level;
import ru.platformer.game.model.LevelListener;
import ru.platformer.game.model.Obstacle;
import ru.platformer.game.model.Tank;

import java.util.ArrayList;

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
}