package ru.platformer.game.model.levelGenerators;

import com.badlogic.gdx.math.GridPoint2;
import org.javatuples.Quartet;
import org.javatuples.Triplet;
import ru.platformer.game.GameObject;
import ru.platformer.game.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class RandomLevelGenerator implements LevelGenerator {
    private final int MAX_X_COORDINATE = 9;
    private final int MAX_Y_COORDINATE = 6;
    private final Random rand;
    private final List<LevelListener> levelListeners;
    private final int numObstacles;
    private final int numTanks;
    private final ArrayList<Tank> tanks = new ArrayList<>();
    private final ArrayList<Obstacle> obstacles = new ArrayList<>();
    private final CollisionDetector collisionDetector;

    public RandomLevelGenerator(List<LevelListener> levelListeners, CollisionDetector collisionDetector,
                                int numObstacles, int numTank) {
        this.levelListeners = levelListeners;
        this.collisionDetector = collisionDetector;

        rand = new Random();
        this.numObstacles = numObstacles;
        this.numTanks = numTank;
    }

    @Override
    public Quartet<Level, Tank, List<Tank>, List<Obstacle>> generate() {
        Level level = new Level(levelListeners, MAX_Y_COORDINATE, MAX_X_COORDINATE);
        LevelGenerator.initBorder(collisionDetector, MAX_X_COORDINATE, MAX_Y_COORDINATE);

        createTanks(level);
        createObstacles(level);


        return new Quartet<>(level, tanks.get(0), tanks.subList(1, tanks.size()), obstacles);
    }

    private void createObstacles(Level level) {
        for (int i = 0; i < numObstacles; i++) {
            Obstacle obstacle = new Obstacle(createRandomCoordinates());
            if (collisionDetector.collisionExist(obstacle.getCurrentCoordinates())) {
                continue;
            }
            obstacles.add(obstacle);
            level.addGameObject(obstacle);
            collisionDetector.addCoordinates(obstacle.getCurrentCoordinates());
        }
    }

    private void createTanks(Level level) {
        for (int i = 0; i < numTanks; i++) {
            Tank tank = new Tank(createRandomCoordinates());
            if (collisionDetector.collisionExist(tank.getCurrentCoordinates())) {
                continue;
            }
            level.addGameObject(tank);
            tanks.add(tank);
            collisionDetector.addCoordinates(tank.getCurrentCoordinates());
        }
    }

    private GridPoint2 createRandomCoordinates() {
        int randX = rand.nextInt(MAX_X_COORDINATE);
        int randY = rand.nextInt(MAX_Y_COORDINATE);

        return new GridPoint2(randX, randY);
    }
}
