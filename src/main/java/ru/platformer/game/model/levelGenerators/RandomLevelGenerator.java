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
import org.javatuples.Pair;


public class RandomLevelGenerator implements LevelGenerator {
    private final int MAX_X_COORDINATE = 9;
    private final int MAX_Y_COORDINATE = 6;
    private final Random rand;
    private final ArrayList<LevelListener> levelListeners;
    private final int numObstacles;
    private final int numAI;
    private GameObject player;

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
    public Pair<Level, GameObject> generate() {
        Level level = new Level(levelListeners);
        createPlayer(level);
        createAI(level);
        createObstacles(level);

        return new Pair<>(level, player);
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
        player = new Tank(createRandomCoordinates());
        level.addGameObject(player);
    }

    private GridPoint2 createRandomCoordinates(){
        int randX = rand.nextInt(MAX_X_COORDINATE);
        int randY = rand.nextInt(MAX_Y_COORDINATE);

        return new GridPoint2(randX, randY);
    }
}
