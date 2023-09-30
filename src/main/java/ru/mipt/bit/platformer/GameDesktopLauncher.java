package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.action.Action;
import ru.mipt.bit.platformer.action.ActionController;
import ru.mipt.bit.platformer.gameEntities.GameEntity;
import ru.mipt.bit.platformer.gameEntities.Level;
import ru.mipt.bit.platformer.graphics.LevelGraphics;
import ru.mipt.bit.platformer.graphics.ObstacleGraphics;
import ru.mipt.bit.platformer.graphics.TankGraphics;
import ru.mipt.bit.platformer.action.InputController;
import ru.mipt.bit.platformer.gameEntities.Obstacle;
import ru.mipt.bit.platformer.gameEntities.Tank;

import java.util.HashMap;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;

public class GameDesktopLauncher implements ApplicationListener {
    private LevelGraphics levelGraphics;
    private Level level;

    private Obstacle obstacle;
    private Tank tank;
    private InputController inputController;
    private ActionController actionController;
    private TankGraphics tankGraphics;
    private ObstacleGraphics obstacleGraphics;


    @Override
    public void create() {
        levelGraphics = new LevelGraphics();
        level = new Level();
        initGameEntities();
        initControllers();
    }

    @Override
    public void render() {
        clearScreen();
        // get time passed since the last render
        float deltaTime = Gdx.graphics.getDeltaTime();
        HashMap<GameEntity, Action> actions =  actionController.generateGameEntitiesActions();
        actionController.applyActions(actions);

        level.updateState(deltaTime);
        levelGraphics.render();
    }

    private void initControllers() {
        inputController = new InputController();
        inputController.initKeyBoardMappings();
        actionController = new ActionController(inputController);
    }

    private void initGameEntities() {
        tank = new Tank(
                new GridPoint2(1, 1)
        );
        tankGraphics = new TankGraphics("images/tank_blue.png", tank);
        level.addGameEntity(tank);
        levelGraphics.addEntityGraphics(tankGraphics);
        inputController.addGameEntity(tank);


        obstacle = new Obstacle(new GridPoint2(1, 3));
        obstacleGraphics = new ObstacleGraphics("images/greenTree.png", obstacle);
        level.addGameEntity(obstacle);
        levelGraphics.addEntityGraphics(obstacleGraphics);
    }


    private static void clearScreen() {
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {
        // do not react to window resizing
    }

    @Override
    public void pause() {
        // game doesn't get paused
    }

    @Override
    public void resume() {
        // game doesn't get paused
    }

    @Override
    public void dispose() {
        levelGraphics.dispose();
    }

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        // level width: 10 tiles x 128px, height: 8 tiles x 128px
        config.setWindowedMode(1280, 1024);
        new Lwjgl3Application(new GameDesktopLauncher(), config);
    }
}
