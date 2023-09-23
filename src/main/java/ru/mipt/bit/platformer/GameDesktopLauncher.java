package ru.mipt.bit.platformer;

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
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Interpolation;
import ru.mipt.bit.platformer.inputControls.Direction;
import ru.mipt.bit.platformer.graphics.Graphics;
import ru.mipt.bit.platformer.inputControls.InputController;
import ru.mipt.bit.platformer.gameEntities.Obstacle;
import ru.mipt.bit.platformer.gameEntities.Tank;
import ru.mipt.bit.platformer.util.TileMovement;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class GameDesktopLauncher implements ApplicationListener {
    private TiledMap level;
    private MapRenderer levelRenderer;
    private TileMovement tileMovement;

    private TiledMapTileLayer groundLayer;

    private Batch batch;

    private Obstacle obstacle;
    private Tank tank;
    private InputController inputController;

    private Graphics tankGraphics;
    private Graphics obstacleGraphics;


    @Override
    public void create() {
        batch = new SpriteBatch();

        initLevelGraphics();

        initGameEntities();

        inputController = new InputController();
        inputController.initKeyBoardMappings();

    }

    private void initLevelGraphics() {
        // load level tiles
        level = new TmxMapLoader().load("level.tmx");
        levelRenderer = createSingleLayerMapRenderer(level, batch);
        groundLayer = getSingleLayer(level);
        tileMovement = new TileMovement(groundLayer, Interpolation.smooth);
    }

    private void initGameEntities() {
        tank = new Tank(
                new GridPoint2(1, 1)
        );
        tankGraphics = new Graphics("images/tank_blue.png", tank);


        obstacle = new Obstacle(new GridPoint2(1, 3));
        obstacleGraphics = new Graphics("images/greenTree.png", obstacle);
        moveRectangleAtTileCenter(
                groundLayer,
                obstacleGraphics.getRectangle(),
                obstacle.getCurrentCoordinates()
        );
    }


    @Override
    public void render() {
        clearScreen();

        // get time passed since the last render
        float deltaTime = Gdx.graphics.getDeltaTime();

        updateGameState(deltaTime);

        renderGame();

    }

    private void updateGameState(float deltaTime) {
        updateTankState(deltaTime);
        updateGameGraphics();

    }

    private void updateGameGraphics() {
        // calculate interpolated player screen coordinates
        tileMovement.moveRectangleBetweenTileCenters(
                tankGraphics.getRectangle(),
                tank.getCurrentCoordinates(),
                tank.getDestinationCoordinates(),
                tank.getMovementProgress()
        );

    }

    private void updateTankState(float deltaTime) {
        Direction newDirection = inputController.getDirection();
        // в будущем у танка будем обновлять состояния и это не только движение, например обновить жизни
        moveTank(newDirection, deltaTime);

    }

    private void moveTank(Direction newDirection, float deltaTime) {
        if (!tank.isMoving() && newDirection != null) {
            GridPoint2 destinationCoordinates = newDirection.apply(tank.getCurrentCoordinates());
            if (!collides(destinationCoordinates, obstacle.getCurrentCoordinates())){
                tank.moveTo(destinationCoordinates);
            }
            tank.setRotation(newDirection.getRotation());
        }
        tank.updateMovementState(deltaTime);

    }


    private void renderGame() {
        // render each tile of the level
        levelRenderer.render();

        // start recording all drawing commands
        batch.begin();

        obstacleGraphics.drawTexture(batch);
        tankGraphics.drawTexture(batch);

        // submit all drawing requests
        batch.end();
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
        // dispose of all the native resources (classes which implement com.badlogic.gdx.utils.Disposable)
        tankGraphics.dispose();
        obstacleGraphics.dispose();
        level.dispose();
        batch.dispose();
    }

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        // level width: 10 tiles x 128px, height: 8 tiles x 128px
        config.setWindowedMode(1280, 1024);
        new Lwjgl3Application(new GameDesktopLauncher(), config);
    }
}
