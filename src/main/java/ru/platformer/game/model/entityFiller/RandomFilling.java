package ru.platformer.game.model.entityFiller;

import com.badlogic.gdx.math.GridPoint2;
import ru.platformer.game.graphics.LevelGraphics;
import ru.platformer.game.graphics.ObstacleGraphics;
import ru.platformer.game.graphics.TankGraphics;
import ru.platformer.game.model.Level;
import ru.platformer.game.model.Obstacle;
import ru.platformer.game.model.Tank;
import ru.platformer.game.entityControllers.AIController;
import ru.platformer.game.entityControllers.PlayerController;

import java.util.Random;

public class RandomFilling implements FillingStrategy{
    private final Random rand;

    public RandomFilling() {
        rand = new Random();
    }

    @Override
    public void createEntities(Level level, LevelGraphics levelGraphics, PlayerController playerController, AIController aiController) {
        createPlayer(level, levelGraphics, playerController);
        createAI(level, levelGraphics, aiController);
        createObstacles(level, levelGraphics);
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

    private void createAI(Level level, LevelGraphics levelGraphics, AIController aiController) {
        // in future
    }

    private void createPlayer(Level level, LevelGraphics levelGraphics, PlayerController playerController) {
        Tank tank = new Tank(createRandomCoordinates());

        TankGraphics tankGraphics = new TankGraphics("images/tank_blue.png", tank, levelGraphics.getTileMovement());
        level.addGameEntity(tank);
        levelGraphics.addEntityGraphics(tankGraphics);
        playerController.addGameEntity(tank);
    }

    private GridPoint2 createRandomCoordinates(){
        int randX = rand.nextInt(7);
        int randY = rand.nextInt(7);
        return new GridPoint2(randX, randY);
    }
}
