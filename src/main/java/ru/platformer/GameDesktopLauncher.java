package ru.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import org.awesome.ai.strategy.NotRecommendingAI;
import org.javatuples.Quartet;
import ru.platformer.game.Action;
import ru.platformer.game.GameObject;
import ru.platformer.game.entityControllers.aiControllers.AwesomeAIControllerAdapter;
import ru.platformer.game.entityControllers.PlayerController;
import ru.platformer.game.entityControllers.aiControllers.RandomAIController;
import ru.platformer.game.graphics.graphicsObjects.stategies.ObstacleGraphicsStrategy;
import ru.platformer.game.graphics.graphicsObjects.stategies.TankGraphicsStrategy;
import ru.platformer.game.model.*;
import ru.platformer.game.model.actions.ActionManager;
import ru.platformer.game.graphics.LevelGraphics;
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
        createLevelGraphics();
        createLevel();
    }

    private void createLevel() {
        CollisionDetector collisionDetector = new CollisionDetector();

        List<LevelListener> levelListeners = List.of(levelGraphics, collisionDetector);

        Quartet<Level, Tank, List<Tank>, List<Obstacle>> levelPlayerAIObstacles = new RandomLevelGenerator(
                levelListeners, collisionDetector, 5, 10
        ).generate();

        parseLevelGenerator(levelPlayerAIObstacles, collisionDetector);
    }

    private void parseLevelGenerator(
            Quartet<Level, Tank, List<Tank>, List<Obstacle>> levelPlayerAIObstacles,
            CollisionDetector collisionDetector
    ) {
        level = levelPlayerAIObstacles.getValue0();
        Tank player = levelPlayerAIObstacles.getValue1();
        List<Tank> bots = levelPlayerAIObstacles.getValue2();

        actionManager = new ActionManager();

        createPlayerController(collisionDetector, player);

        createAIRandomController(collisionDetector, bots);

//        List<Obstacle> obstacles = levelPlayerAIObstacles.getValue3();
//        createAIAwesomeController(collisionDetector, player, bots, obstacles);
    }

    private void createPlayerController(CollisionDetector collisionDetector, Tank player) {
        PlayerController playerController = new PlayerController(player);
        Initializer.initKeyBoardMappings(playerController, collisionDetector);
        actionManager.addEntityActionController(playerController);
    }

    private void createAIRandomController(CollisionDetector collisionDetector, List<Tank> bots) {
        for (GameObject AIGameObject: bots){
            RandomAIController aiController = new RandomAIController(AIGameObject);
            actionManager.addEntityActionController(aiController);
            Initializer.initAIEventMappings(aiController, collisionDetector);
        }
    }

    private void createAIAwesomeController(CollisionDetector collisionDetector, Tank player, List<Tank> bots, List<Obstacle> obstacles) {
        AwesomeAIControllerAdapter awesomeAIControllerAdapter = new AwesomeAIControllerAdapter(
                new NotRecommendingAI(),
                player,
                bots,
                obstacles,
                level.getWidth(),
                level.getHeight()
        );
        Initializer.initAIEventMappings(awesomeAIControllerAdapter, collisionDetector);
        actionManager.addEntityActionController(awesomeAIControllerAdapter);
    }

    private void createLevelGraphics() {
        levelGraphics = new LevelGraphics();
        levelGraphics.addGraphicsStrategyMapping(Tank.class, new TankGraphicsStrategy(
                "images/tank_blue.png",
                levelGraphics.getTileMovement()
        ));
        levelGraphics.addGraphicsStrategyMapping(Obstacle.class, new ObstacleGraphicsStrategy(
                "images/greenTree.png",
                levelGraphics.getGroundLayer()
        ));
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
