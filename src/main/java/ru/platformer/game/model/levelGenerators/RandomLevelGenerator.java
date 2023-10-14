package ru.platformer.game.model.levelGenerators;

import com.badlogic.gdx.math.GridPoint2;
import ru.platformer.game.GameObject;
import ru.platformer.game.graphics.LevelGraphics;
import ru.platformer.game.graphics.ObstacleGraphics;
import ru.platformer.game.graphics.TankGraphics;
import ru.platformer.game.model.Level;
import ru.platformer.game.model.Obstacle;
import ru.platformer.game.model.Tank;

import java.util.Random;

public class RandomLevelGenerator implements LevelGenerator {
    private final int MAX_X_COORDINATE = 9;
    private final int MAX_Y_COORDINATE = 6;
    private final Random rand;
    private GameObject player;

    public RandomLevelGenerator() {
        rand = new Random();
    }

    @Override
    public LevelGeneratorInfo generate() {
        Level level = new Level();
        LevelGraphics levelGraphics = new LevelGraphics();
        createPlayer(level, levelGraphics);
        createAI(level, levelGraphics);
        createObstacles(level, levelGraphics);

        return new LevelGeneratorInfo(
                level,
                levelGraphics,
                player
        );
    }

    private void createObstacles(Level level, LevelGraphics levelGraphics) {
        for (int i = 0; i < rand.nextInt(10); i ++){
            Obstacle obstacle = new Obstacle(createRandomCoordinates());
            ObstacleGraphics obstacleGraphics = new ObstacleGraphics(
                    "images/greenTree.png", obstacle, levelGraphics.getGroundLayer()
            );
            if (level.collisionExist(obstacle.getCurrentCoordinates())){
                continue;
            }
            level.addGameEntity(obstacle);
            levelGraphics.addEntityGraphics(obstacleGraphics);
        }
    }

    private void createAI(Level level, LevelGraphics levelGraphics) {
        // in future
    }

    private void createPlayer(Level level, LevelGraphics levelGraphics) {
        player = new Tank(createRandomCoordinates());

        TankGraphics tankGraphics = new TankGraphics(
                "images/tank_blue.png",
                (Tank) player,
                levelGraphics.getTileMovement()
        );
        level.addGameEntity(player);
        levelGraphics.addEntityGraphics(tankGraphics);
    }

    private GridPoint2 createRandomCoordinates(){
        int randX = rand.nextInt(MAX_X_COORDINATE);
        int randY = rand.nextInt(MAX_Y_COORDINATE);

        return new GridPoint2(randX, randY);
    }
}
