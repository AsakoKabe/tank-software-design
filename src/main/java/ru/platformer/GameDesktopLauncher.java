package ru.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import org.awesome.ai.strategy.NotRecommendingAI;
import org.javatuples.Triplet;
import ru.platformer.game.Action;
import ru.platformer.game.GameObject;
import ru.platformer.game.entityControllers.aiControllers.AwesomeAIControllerAdapter;
import ru.platformer.game.entityControllers.aiControllers.RandomAIController;
import ru.platformer.game.entityControllers.PlayerController;
import ru.platformer.game.model.*;
import ru.platformer.game.model.actions.ActionManager;
import ru.platformer.game.graphics.LevelGraphics;
import ru.platformer.game.model.levelGenerators.FileLevelGenerator;
import ru.platformer.game.model.levelGenerators.RandomLevelGenerator;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;

public class GameDesktopLauncher implements ApplicationListener {
    private LevelGraphics levelGraphics;
    private Level level;
    private ActionManager actionManager;

    @Override
    public void create() {
        ArrayList<LevelListener> levelListeners = new ArrayList<>();
        levelGraphics = new LevelGraphics();
        CollisionDetector collisionDetector = new CollisionDetector();
        levelListeners.add(levelGraphics);
        levelListeners.add(collisionDetector);

//        Triplet<Level, Tank, List<Tank>> levelPlayerAI = new FileLevelGenerator(levelListeners, collisionDetector,  "src/main/resources/level.txt").generate();
        Triplet<Level, Tank, List<Tank>> levelPlayerAI = new RandomLevelGenerator(levelListeners,
                collisionDetector, 5, 10
        ).generate();
        level = levelPlayerAI.getValue0();
        Tank player = levelPlayerAI.getValue1();
        List<Tank> bots = levelPlayerAI.getValue2();


        actionManager = new ActionManager();

        PlayerController playerController = new PlayerController(player);
        actionManager.addEntityActionController(playerController);

//        random actions
//        for (GameObject AIGameObject: bots){
//            RandomAIController aiController = new RandomAIController(AIGameObject);
//            actionManager.addEntityActionController(aiController);
//            Initializer.initAIEventMappings(aiController, collisionDetector);
//        }

//        ai from lib

        AwesomeAIControllerAdapter awesomeAIControllerAdapter = new AwesomeAIControllerAdapter(
                new NotRecommendingAI(),
                player,
                bots,
                null,
                level.getWidth(),
                level.getHeight()
        );
        Initializer.initAIEventMappings(awesomeAIControllerAdapter, collisionDetector);

        Initializer.initKeyBoardMappings(playerController, collisionDetector);
    }


    @Override
    public void render() {
        clearScreen();
        // get time passed since the last render
        float deltaTime = Gdx.graphics.getDeltaTime();
        ArrayList<Action> actions = actionManager.generateGameObjectsActions();
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
