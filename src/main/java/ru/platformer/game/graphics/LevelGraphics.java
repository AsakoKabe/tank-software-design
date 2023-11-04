package ru.platformer.game.graphics;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Interpolation;
import ru.platformer.game.GameObject;
import ru.platformer.game.model.LevelListener;
import ru.platformer.util.TileMovement;

import java.util.*;

import static ru.platformer.util.GdxGameUtils.createSingleLayerMapRenderer;
import static ru.platformer.util.GdxGameUtils.getSingleLayer;

public class LevelGraphics implements LevelListener {
    private final TiledMap tiledMap;
    private final MapRenderer levelRenderer;

    private final TileMovement tileMovement;

    private final TiledMapTileLayer groundLayer;
    private final Batch batch;

    private final Map<GameObject, GameObjectGraphics> graphicsByGameObject = new LinkedHashMap<>();;

    private final Map<Class<? extends GameObject>, GraphicsStrategy> graphicsStrategyByGameObject = new HashMap<>();


    public LevelGraphics() {
        batch = new SpriteBatch();
        tiledMap = new TmxMapLoader().load("level.tmx");
        levelRenderer = createSingleLayerMapRenderer(tiledMap, batch);
        groundLayer = getSingleLayer(tiledMap);
        tileMovement = new TileMovement(groundLayer, Interpolation.smooth);
    }

    public void addGraphicsStrategyMapping(
            Class<? extends GameObject> clazz,
            GraphicsStrategy graphicsStrategy){
        graphicsStrategyByGameObject.put(clazz, graphicsStrategy);
    }

    @Override
    public void onAddGameObject(GameObject gameObject) {
        Class<?> objectType = gameObject.getClass();
        GameObjectGraphics gameObjectGraphics = graphicsStrategyByGameObject.get(objectType).create(gameObject);
        this.graphicsByGameObject.put(gameObject, gameObjectGraphics);
    }

    @Override
    public void onDeleteGameObject(GameObject gameObject) {
        graphicsByGameObject.remove(gameObject);
    }

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

    public void dispose() {
        // dispose of all the native resources (classes which implement com.badlogic.gdx.utils.Disposable)
        for (GameObjectGraphics gameObjectGraphics : graphicsByGameObject.values()) {
            gameObjectGraphics.dispose();
        }
        tiledMap.dispose();
        batch.dispose();
    }

    public TileMovement getTileMovement() {
        return tileMovement;
    }

    public TiledMapTileLayer getGroundLayer() {
        return groundLayer;
    }

}
