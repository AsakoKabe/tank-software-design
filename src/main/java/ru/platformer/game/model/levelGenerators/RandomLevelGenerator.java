package ru.platformer.game.model.levelGenerators;

import com.badlogic.gdx.math.GridPoint2;
import org.javatuples.Triplet;
import ru.platformer.game.GameObject;
import ru.platformer.game.model.*;

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
    private final ArrayList<GameObject> aiGameObjects = new ArrayList<>();
    private final CollisionDetector collisionDetector;

    public RandomLevelGenerator(ArrayList<LevelListener> levelListeners, CollisionDetector collisionDetector, int numObstacles, int numAI) {
        this.levelListeners = levelListeners;
        this.collisionDetector = collisionDetector;

        rand = new Random();
        this.numObstacles = numObstacles;
        this.numAI = numAI;
    }

    @Override
    public Triplet<Level, GameObject, ArrayList<GameObject>> generate() {
        Level level = new Level(levelListeners);
        LevelGenerator.initBorder(level, MAX_X_COORDINATE, MAX_Y_COORDINATE);

        createPlayer(level);
        createAI(level);
        createObstacles(level);


        return new Triplet<>(level, player, aiGameObjects);
    }

    private void createObstacles(Level level) {
        for (int i = 0; i < rand.nextInt(numObstacles); i++) {
            Obstacle obstacle = new Obstacle(createRandomCoordinates());
            if (collisionDetector.collisionExist(obstacle.getCurrentCoordinates())) {
                continue;
            }
            level.addGameObject(obstacle);
            collisionDetector.addCoordinates(obstacle.getCurrentCoordinates());
        }
    }

    private void createAI(Level level) {
        for (int i = 0; i < rand.nextInt(numAI); i++) {
            Tank tank = new Tank(createRandomCoordinates());
            if (collisionDetector.collisionExist(tank.getCurrentCoordinates())) {
                continue;
            }
            level.addGameObject(tank);
            aiGameObjects.add(tank);
            collisionDetector.addCoordinates(tank.getCurrentCoordinates());
        }
    }

    private void createPlayer(Level level) {
        player = new Tank(createRandomCoordinates());
        level.addGameObject(player);
        collisionDetector.addCoordinates(player.getCurrentCoordinates());
    }

    private GridPoint2 createRandomCoordinates() {
        int randX = rand.nextInt(MAX_X_COORDINATE);
        int randY = rand.nextInt(MAX_Y_COORDINATE);

        return new GridPoint2(randX, randY);
    }
}
