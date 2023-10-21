package ru.platformer.game.graphics;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Interpolation;
import ru.platformer.game.GameObject;
import ru.platformer.game.model.LevelListener;
import ru.platformer.game.model.Obstacle;
import ru.platformer.game.model.Tank;
import ru.platformer.util.TileMovement;

import java.util.ArrayList;
import java.util.List;

import static ru.platformer.util.GdxGameUtils.createSingleLayerMapRenderer;
import static ru.platformer.util.GdxGameUtils.getSingleLayer;

public class LevelGraphics  implements LevelListener {
    private final TiledMap tiledMap;
    private final MapRenderer levelRenderer;

    private final TileMovement tileMovement;

    private final TiledMapTileLayer groundLayer;
    private final Batch batch;

    private final List<GameObjectGraphics> gameObjectsGraphics;


    public LevelGraphics() {
        batch = new SpriteBatch();
        tiledMap = new TmxMapLoader().load("level.tmx");
        levelRenderer = createSingleLayerMapRenderer(tiledMap, batch);
        groundLayer = getSingleLayer(tiledMap);
        tileMovement = new TileMovement(groundLayer, Interpolation.smooth);

        gameObjectsGraphics = new ArrayList<>();
    }


    @Override
    public void onAddGameObject(GameObject gameObject) {
        // TODO: стратегия????
        Class<?> objectType = gameObject.getClass();
        String objectTypeName = objectType.getSimpleName();
        GameObjectGraphics gameObjectGraphics;
        switch (objectTypeName){
            case "Tank":
                gameObjectGraphics = new TankGraphics(
                        "images/tank_blue.png",
                        (Tank) gameObject,
                        tileMovement
                );
                break;
            case "Obstacle":
                gameObjectGraphics = new ObstacleGraphics(
                        "images/greenTree.png",
                        (Obstacle) gameObject,
                        groundLayer
                );
                break;
            default:
                gameObjectGraphics = null;
        }
        this.gameObjectsGraphics.add(gameObjectGraphics);
    }

    public void render() {
        // render each tile of the level
        levelRenderer.render();

        // start recording all drawing commands
        batch.begin();

        for (GameObjectGraphics gameObjectGraphics : gameObjectsGraphics){
            gameObjectGraphics.draw(batch);
        }
        // submit all drawing requests
        batch.end();
    }

    public void dispose() {
        // dispose of all the native resources (classes which implement com.badlogic.gdx.utils.Disposable)
        for (GameObjectGraphics gameObjectGraphics : gameObjectsGraphics){
            gameObjectGraphics.dispose();
        }
        tiledMap.dispose();
        batch.dispose();
    }


}
