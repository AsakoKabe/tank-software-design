package ru.platformer.game.model.levelGenerators;

import com.badlogic.gdx.math.GridPoint2;
import ru.platformer.game.GameObject;
import ru.platformer.game.graphics.LevelGraphics;
import ru.platformer.game.graphics.ObstacleGraphics;
import ru.platformer.game.graphics.TankGraphics;
import ru.platformer.game.model.Level;
import ru.platformer.game.model.LevelListener;
import ru.platformer.game.model.Obstacle;
import ru.platformer.game.model.Tank;

import java.util.ArrayList;
import java.util.Random;

public class RandomLevelGenerator implements LevelGenerator {
    private final int MAX_X_COORDINATE = 9;
    private final int MAX_Y_COORDINATE = 6;
    private final Random rand;
    private final ArrayList<LevelListener> levelListeners;
    private final int numObstacles;
    private final int numAI;

    public RandomLevelGenerator(
            ArrayList<LevelListener> levelListeners,
            int numObstacles,
            int numAI
    ) {
        this.levelListeners = levelListeners;

        rand = new Random();
        this.numObstacles = numObstacles;
        this.numAI = numAI;
    }

    @Override
    public Level generate() {
        Level level = new Level(levelListeners);
        createPlayer(level);
        createAI(level);
        createObstacles(level);

        return level;
    }

    private void createObstacles(Level level) {
        for (int i = 0; i < rand.nextInt(numObstacles); i ++){
            Obstacle obstacle = new Obstacle(createRandomCoordinates());
            if (level.collisionExist(obstacle.getCurrentCoordinates())){
                continue;
            }
            level.addGameObject(obstacle);
        }
    }

    private void createAI(Level level) {
        // in future
    }

    private void createPlayer(Level level) {
        GameObject player = new Tank(createRandomCoordinates());
        level.setPlayerGameObject(player);
        level.addGameObject(player);
    }

    private GridPoint2 createRandomCoordinates(){
        int randX = rand.nextInt(MAX_X_COORDINATE);
        int randY = rand.nextInt(MAX_Y_COORDINATE);

        return new GridPoint2(randX, randY);
    }
}
