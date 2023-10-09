package ru.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import ru.platformer.game.Action;
import ru.platformer.game.model.actions.PlayerController;
import ru.platformer.game.model.actions.ActionManager;
import ru.platformer.game.model.Level;
import ru.platformer.game.graphics.LevelGraphics;

import java.util.ArrayList;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;

public class GameDesktopLauncher implements ApplicationListener {
    private LevelGraphics levelGraphics;
    private Level level;
    private ActionManager actionManager;

    @Override
    public void create() {
        levelGraphics = new LevelGraphics();
        level = new Level();
        PlayerController playerController = new PlayerController();
        actionManager = new ActionManager();
        actionManager.addEntityActionController(playerController);

        Initializer.initGameEntities(level, levelGraphics, playerController);
        Initializer.initKeyBoardMappings(playerController, level);
    }

    @Override
    public void render() {
        clearScreen();
        // get time passed since the last render
        float deltaTime = Gdx.graphics.getDeltaTime();
        ArrayList<Action> actions = actionManager.generateGameEntitiesActions();
        actionManager.applyActions(actions);

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
