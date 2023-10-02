package ru.mipt.bit.platformer;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.action.Direction;
import ru.mipt.bit.platformer.action.InputController;
import ru.mipt.bit.platformer.action.MoveFactory;
import ru.mipt.bit.platformer.gameEntities.Level;
import ru.mipt.bit.platformer.gameEntities.Obstacle;
import ru.mipt.bit.platformer.gameEntities.Tank;
import ru.mipt.bit.platformer.graphics.LevelGraphics;
import ru.mipt.bit.platformer.graphics.ObstacleGraphics;
import ru.mipt.bit.platformer.graphics.TankGraphics;

import static com.badlogic.gdx.Input.Keys.*;
import static com.badlogic.gdx.Input.Keys.D;

public class Initializer {
    public static void initKeyBoardMappings(InputController inputController, Level level) {
        inputController.addKeyActionFactoryMapping(UP, new MoveFactory(Direction.UP, level));
        inputController.addKeyActionFactoryMapping(W, new MoveFactory(Direction.UP, level));
        inputController.addKeyActionFactoryMapping(LEFT, new MoveFactory(Direction.LEFT, level));
        inputController.addKeyActionFactoryMapping(A, new MoveFactory(Direction.LEFT, level));
        inputController.addKeyActionFactoryMapping(DOWN, new MoveFactory(Direction.DOWN, level));
        inputController.addKeyActionFactoryMapping(S, new MoveFactory(Direction.DOWN, level));
        inputController.addKeyActionFactoryMapping(RIGHT, new MoveFactory(Direction.RIGHT, level));
        inputController.addKeyActionFactoryMapping(D, new MoveFactory(Direction.RIGHT, level));
    }

    public static void initGameEntities(Level level, LevelGraphics levelGraphics, InputController inputController) {
        Tank tank = new Tank(new GridPoint2(1, 1));
        TankGraphics tankGraphics = new TankGraphics("images/tank_blue.png", tank, levelGraphics.getTileMovement());
        level.addGameEntity(tank);
        levelGraphics.addEntityGraphics(tankGraphics);
        inputController.addGameEntity(tank);


        Obstacle obstacle = new Obstacle(new GridPoint2(1, 3));
        ObstacleGraphics obstacleGraphics = new ObstacleGraphics(
                "images/greenTree.png", obstacle, levelGraphics.getGroundLayer()
        );
        level.addGameEntity(obstacle);
        levelGraphics.addEntityGraphics(obstacleGraphics);
    }

}
