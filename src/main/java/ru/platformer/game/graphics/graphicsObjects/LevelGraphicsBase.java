package ru.platformer.game.graphics.graphicsObjects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import ru.platformer.game.GameObject;
import ru.platformer.game.graphics.GameObjectGraphics;
import ru.platformer.game.graphics.GraphicsFactory;
import ru.platformer.game.graphics.LevelGraphics;
import ru.platformer.game.model.LevelListener;
import ru.platformer.util.TileMovement;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class LevelGraphicsBase implements LevelGraphics, LevelListener {
    private final TiledMap tiledMap;
    private final MapRenderer levelRenderer;

    private final TileMovement tileMovement;

    private final TiledMapTileLayer groundLayer;
    private final Batch batch;

    private final Map<GameObject, GameObjectGraphics> graphicsByGameObject = new LinkedHashMap<>();
    ;

    private final Map<Class<? extends GameObject>, GraphicsFactory> graphicsStrategyByGameObject = new HashMap<>();


    public LevelGraphicsBase(
            Batch batch,
            TiledMap tiledMap,
            MapRenderer mapRenderer,
            TiledMapTileLayer groundLayer,
            TileMovement tileMovement
    ) {
        this.batch = batch;
        this.groundLayer = groundLayer;
        this.levelRenderer = mapRenderer;
        this.tiledMap = tiledMap;
        this.tileMovement = tileMovement;
    }

    @Override
    public void addGraphicsStrategyMapping(
            Class<? extends GameObject> clazz,
            GraphicsFactory graphicsFactory
    ) {
        graphicsStrategyByGameObject.put(clazz, graphicsFactory);
    }

    @Override
    public void onAddGameObject(GameObject gameObject) {
        Class<?> objectType = gameObject.getClass();
        GameObjectGraphics gameObjectGraphics =
                graphicsStrategyByGameObject.get(objectType).create(gameObject);
        this.graphicsByGameObject.put(gameObject, gameObjectGraphics);
    }

    @Override
    public void onDeleteGameObject(GameObject gameObject) {
        graphicsByGameObject.remove(gameObject);
    }

    @Override
    public void render() {
        // render each tile of the level
        levelRenderer.render();

        // start recording all drawing commands
        batch.begin();

        for (GameObjectGraphics gameObjectGraphics : graphicsByGameObject.values()) {
            gameObjectGraphics.draw(batch);
        }

        // submit all drawing requests
        batch.end();
    }

    public Map<GameObject, GameObjectGraphics> getGraphicsByGameObject() {
        return graphicsByGameObject;
    }

    @Override
    public Batch getBatch() {
        return batch;
    }

    @Override
    public void dispose() {
        // dispose of all the native resources (classes which implement com.badlogic.gdx.utils
        // .Disposable)
        for (GameObjectGraphics gameObjectGraphics : graphicsByGameObject.values()) {
            gameObjectGraphics.dispose();
        }
        tiledMap.dispose();
        batch.dispose();
    }

}
