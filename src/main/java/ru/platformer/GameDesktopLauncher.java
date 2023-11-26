package ru.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Interpolation;
import org.awesome.ai.strategy.NotRecommendingAI;
import org.javatuples.Quartet;
import ru.platformer.game.Action;
import ru.platformer.game.ActionManager;
import ru.platformer.game.GameObject;
import ru.platformer.game.graphics.LevelGraphics;
import ru.platformer.game.graphics.actions.toggle.ToggleActionFactory;
import ru.platformer.game.graphics.graphicsObjects.LevelGraphicsBase;
import ru.platformer.game.graphics.decorators.LevelGraphicsHealthBarDecorator;
import ru.platformer.game.graphics.graphicsObjects.creationStategies.BulletGraphicsFactory;
import ru.platformer.game.graphics.graphicsObjects.creationStategies.ExplosionGraphicsStrategies;
import ru.platformer.game.graphics.graphicsObjects.creationStategies.ObstacleGraphicsFactory;
import ru.platformer.game.graphics.graphicsObjects.creationStategies.TankGraphicsFactory;
import ru.platformer.game.model.CollisionDetector;
import ru.platformer.game.model.Explosion;
import ru.platformer.game.model.LevelListener;
import ru.platformer.game.model.entityControllers.KeyboardController;
import ru.platformer.game.model.entityControllers.aiControllers.AwesomeAIControllerAdapter;
import ru.platformer.game.model.entityControllers.aiControllers.RandomAIController;
import ru.platformer.game.model.levelGenerators.RandomLevelGenerator;
import ru.platformer.game.model.objects.Bullet;
import ru.platformer.game.model.objects.Level;
import ru.platformer.game.model.objects.Obstacle;
import ru.platformer.game.model.objects.Tank;
import ru.platformer.util.TileMovement;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.Input.Keys.L;
import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static ru.platformer.util.GdxGameUtils.createSingleLayerMapRenderer;
import static ru.platformer.util.GdxGameUtils.getSingleLayer;

public class GameDesktopLauncher implements ApplicationListener {
    private LevelGraphics levelGraphics;
    private Level level;
    private ActionManager actionManager;

    private static void clearScreen() {
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
    }

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        // level width: 10 tiles x 128px, height: 8 tiles x 128px
        config.setWindowedMode(1280, 1024);
        new Lwjgl3Application(new GameDesktopLauncher(), config);
    }

    @Override
    public void create() {
        actionManager = new ActionManager();
        createLevelGraphics();
        createLevel();
    }

    private void createLevel() {
        CollisionDetector collisionDetector = new CollisionDetector();

        List<LevelListener> levelListeners = List.of(levelGraphics, collisionDetector);

        Quartet<Level, Tank, List<Tank>, List<Obstacle>> levelPlayerAIObstacles =
                new RandomLevelGenerator(
                        levelListeners, collisionDetector, 1, 3
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

        createPlayerController(collisionDetector, player);
        createAIRandomController(collisionDetector, bots);


//        List<Obstacle> obstacles = levelPlayerAIObstacles.getValue3();
//        createAIAwesomeController(collisionDetector, player, bots, obstacles);
    }

    private void createPlayerController(
            CollisionDetector collisionDetector, Tank player) {

        KeyboardController<GameObject> keyboardController = new KeyboardController<>(player);
        Initializer.initKeyBoardMappings(keyboardController, collisionDetector, level);
        actionManager.addEntityActionController(keyboardController);
    }

    private void createAIRandomController(
            CollisionDetector collisionDetector, List<Tank> bots) {
        for (GameObject AIGameObject : bots) {
            RandomAIController aiController = new RandomAIController(AIGameObject);
            actionManager.addEntityActionController(aiController);
            Initializer.initAIEventMappings(aiController, collisionDetector, level);
        }
    }

    private void createAIAwesomeController(
            CollisionDetector collisionDetector, Tank player,
            List<Tank> bots, List<Obstacle> obstacles
    ) {
        AwesomeAIControllerAdapter awesomeAIControllerAdapter = new AwesomeAIControllerAdapter(
                new NotRecommendingAI(),
                player,
                bots,
                obstacles,
                level.getWidth(),
                level.getHeight()
        );
        Initializer.initAIEventMappings(awesomeAIControllerAdapter, collisionDetector, level);
        actionManager.addEntityActionController(awesomeAIControllerAdapter);
    }

    private void createLevelGraphics() {
        Batch batch = new SpriteBatch();
        TiledMap tiledMap = new TmxMapLoader().load("level.tmx");
        MapRenderer levelRenderer = createSingleLayerMapRenderer(tiledMap, batch);
        TiledMapTileLayer groundLayer = getSingleLayer(tiledMap);
        TileMovement tileMovement = new TileMovement(groundLayer, Interpolation.smooth);

        levelGraphics = new LevelGraphicsBase(
                batch,
                tiledMap,
                levelRenderer,
                groundLayer,
                tileMovement
        );
        levelGraphics = new LevelGraphicsHealthBarDecorator(levelGraphics);
        initGraphicsStrategies(tileMovement, groundLayer);
        initLevelGraphicsController();

    }

    private void initLevelGraphicsController() {
        KeyboardController<LevelGraphics> levelGraphicsController =
                new KeyboardController<>(levelGraphics);
        levelGraphicsController.addKeyActionFactoryMapping(L, new ToggleActionFactory());
        actionManager.addEntityActionController(levelGraphicsController);
    }

    private void initGraphicsStrategies(TileMovement tileMovement, TiledMapTileLayer groundLayer) {
        levelGraphics.addGraphicsStrategyMapping(Tank.class, new TankGraphicsFactory(
                "images/tank_blue.png",
                tileMovement
        ));
        levelGraphics.addGraphicsStrategyMapping(Obstacle.class, new ObstacleGraphicsFactory(
                "images/greenTree.png",
                groundLayer
        ));
        levelGraphics.addGraphicsStrategyMapping(Bullet.class, new BulletGraphicsFactory(
                "images/bullet.png",
                tileMovement
        ));
        levelGraphics.addGraphicsStrategyMapping(Explosion.class, new ExplosionGraphicsStrategies(
                "images/explosion.png",
                groundLayer
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
}
