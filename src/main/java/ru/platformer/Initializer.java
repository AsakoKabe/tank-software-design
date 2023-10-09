package ru.platformer;

import com.badlogic.gdx.math.GridPoint2;
import ru.platformer.game.Direction;
import ru.platformer.game.model.actions.PlayerController;
import ru.platformer.game.model.actions.move.MoveFactory;
import ru.platformer.game.model.Level;
import ru.platformer.game.model.Obstacle;
import ru.platformer.game.model.Tank;
import ru.platformer.game.graphics.LevelGraphics;
import ru.platformer.game.graphics.ObstacleGraphics;
import ru.platformer.game.graphics.TankGraphics;

import static com.badlogic.gdx.Input.Keys.*;
import static com.badlogic.gdx.Input.Keys.D;

public class Initializer {
    public static void initKeyBoardMappings(PlayerController playerController, Level level) {
        playerController.addKeyActionFactoryMapping(UP, new MoveFactory(Direction.UP, level));
        playerController.addKeyActionFactoryMapping(W, new MoveFactory(Direction.UP, level));
        playerController.addKeyActionFactoryMapping(LEFT, new MoveFactory(Direction.LEFT, level));
        playerController.addKeyActionFactoryMapping(A, new MoveFactory(Direction.LEFT, level));
        playerController.addKeyActionFactoryMapping(DOWN, new MoveFactory(Direction.DOWN, level));
        playerController.addKeyActionFactoryMapping(S, new MoveFactory(Direction.DOWN, level));
        playerController.addKeyActionFactoryMapping(RIGHT, new MoveFactory(Direction.RIGHT, level));
        playerController.addKeyActionFactoryMapping(D, new MoveFactory(Direction.RIGHT, level));
    }

    public static void initGameEntities(Level level, LevelGraphics levelGraphics, PlayerController playerController) {
        Tank tank = new Tank(new GridPoint2(1, 1));
        TankGraphics tankGraphics = new TankGraphics("images/tank_blue.png", tank, levelGraphics.getTileMovement());
        level.addGameEntity(tank);
        levelGraphics.addEntityGraphics(tankGraphics);
        playerController.addGameEntity(tank);


        Obstacle obstacle = new Obstacle(new GridPoint2(1, 3));
        ObstacleGraphics obstacleGraphics = new ObstacleGraphics(
                "images/greenTree.png", obstacle, levelGraphics.getGroundLayer()
        );
        level.addGameEntity(obstacle);
        levelGraphics.addEntityGraphics(obstacleGraphics);
    }

}
