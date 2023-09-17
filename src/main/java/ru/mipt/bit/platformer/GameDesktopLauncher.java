package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.direction.Directions;
import ru.mipt.bit.platformer.gameEntity.GameEntity;
import ru.mipt.bit.platformer.map.OneLevelMap;
import ru.mipt.bit.platformer.gameEntity.Obstacle;
import ru.mipt.bit.platformer.gameEntity.Player;
import ru.mipt.bit.platformer.direction.DirectionController;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class GameDesktopLauncher implements ApplicationListener {

    private static final float MOVEMENT_SPEED = 0.4f;

    private Batch batch;

    private OneLevelMap oneLevelMap;

    private Obstacle obstacle;
    private Player player;
    private DirectionController directionController;
    private final ArrayList<GameEntity> gameEntities = new ArrayList<>();

    public GameDesktopLauncher() {
    }

    @Override
    public void create() {
        batch = new SpriteBatch();

        // load level tiles
        oneLevelMap = new OneLevelMap("level.tmx", batch);

        player = new Player(
                "images/tank_blue.png",
                new GridPoint2(1, 1)
        );
        gameEntities.add(player);

        obstacle = new Obstacle(
                "images/greenTree.png",
                new GridPoint2(1, 3),
                oneLevelMap  // как лучше? кидать map или map.getGroundLayer()
        );
        gameEntities.add(obstacle);

        directionController = new DirectionController();
    }

    @Override
    public void render() {
        clearScreen();

        movePlayer();

        // render each tile of the level
        oneLevelMap.levelRender();

        // start recording all drawing commands
        batch.begin();

        drawTextureGameEntities();

        // submit all drawing requests
        batch.end();

    }

    private void drawTextureGameEntities() {
        for (GameEntity gameEntity: gameEntities){
            drawTextureRegionUnscaled(batch, gameEntity.getGraphics(), gameEntity.getRectangle(), gameEntity.getRotation());
        }
    }

    private void movePlayer() {
        // get time passed since the last render
        float deltaTime = Gdx.graphics.getDeltaTime();

        Directions newDirection = directionController.getNewDirection(Gdx.input);
        if (isEqual(player.getMovementProgress(), 1f) && newDirection != Directions.STAY) {
            GridPoint2 destinationCoordinates = player.getCurrentCoordinates().cpy().add(newDirection.getCoordinates());
            if (!destinationCoordinates.equals(obstacle.getCurrentCoordinates())){
                player.setDestinationCoordinates(destinationCoordinates);
                player.setMovementProgress(0f);
            }
            player.setRotation(newDirection.getRotation());
        }

        // calculate interpolated player screen coordinates
        oneLevelMap.calculateInterpolatedPlayerScreenCoordinates(
                player
        );

        player.setMovementProgress(continueProgress(player.getMovementProgress(), deltaTime, MOVEMENT_SPEED));
        if (isEqual(player.getMovementProgress(), 1f)) {
            // record that the player has reached his/her destination
            player.setCurrentCoordinates(player.getDestinationCoordinates());
        }
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
        obstacle.textureDispose();
        player.textureDispose();
        oneLevelMap.levelDispose();
        batch.dispose();
    }

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        // level width: 10 tiles x 128px, height: 8 tiles x 128px
        config.setWindowedMode(1280, 1024);
        new Lwjgl3Application(new GameDesktopLauncher(), config);
    }
}
