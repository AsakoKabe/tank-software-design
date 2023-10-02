package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.action.*;
import ru.mipt.bit.platformer.gameEntities.GameEntity;
import ru.mipt.bit.platformer.gameEntities.Level;
import ru.mipt.bit.platformer.graphics.LevelGraphics;
import ru.mipt.bit.platformer.graphics.ObstacleGraphics;
import ru.mipt.bit.platformer.graphics.TankGraphics;
import ru.mipt.bit.platformer.gameEntities.Obstacle;
import ru.mipt.bit.platformer.gameEntities.Tank;

import java.util.HashMap;

import static com.badlogic.gdx.Input.Keys.*;
import static com.badlogic.gdx.Input.Keys.D;
import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;

public class GameDesktopLauncher implements ApplicationListener {
    private LevelGraphics levelGraphics;
    private Level level;
    private ActionController actionController;

    @Override
    public void create() {
        levelGraphics = new LevelGraphics();
        level = new Level();
        InputController inputController = new InputController();
        actionController = new ActionController(level, inputController);

        Initializer.initGameEntities(level, levelGraphics, inputController);
        Initializer.initKeyBoardMappings(inputController);
    }

    @Override
    public void render() {
        clearScreen();
        // get time passed since the last render
        float deltaTime = Gdx.graphics.getDeltaTime();
        HashMap<GameEntity, Action> actions = actionController.generateGameEntitiesActions();
        actionController.applyActions(actions);

        level.updateState(deltaTime);
        levelGraphics.render();
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
