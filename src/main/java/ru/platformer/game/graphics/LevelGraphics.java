package ru.platformer.game.graphics;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Interpolation;
import ru.platformer.util.TileMovement;

import java.util.ArrayList;
import java.util.List;

import static ru.platformer.util.GdxGameUtils.createSingleLayerMapRenderer;
import static ru.platformer.util.GdxGameUtils.getSingleLayer;

public class LevelGraphics {
    private final TiledMap level;
    private final MapRenderer levelRenderer;

    private final TileMovement tileMovement;

    private final TiledMapTileLayer groundLayer;
    private final Batch batch;

    private final List<EntityGraphics> entitiesGraphics;


    public LevelGraphics() {
        batch = new SpriteBatch();
        level = new TmxMapLoader().load("level.tmx");
        levelRenderer = createSingleLayerMapRenderer(level, batch);
        groundLayer = getSingleLayer(level);
        tileMovement = new TileMovement(groundLayer, Interpolation.smooth);

        entitiesGraphics = new ArrayList<>();
    }


    public void addEntityGraphics(EntityGraphics entityGraphics){
        entitiesGraphics.add(entityGraphics);
    }

    public void render() {
        // render each tile of the level
        levelRenderer.render();

        // start recording all drawing commands
        batch.begin();

        for (EntityGraphics entityGraphics: entitiesGraphics){
            entityGraphics.draw(batch);
        }
        // submit all drawing requests
        batch.end();
    }

    public void dispose() {
        // dispose of all the native resources (classes which implement com.badlogic.gdx.utils.Disposable)
        for (EntityGraphics entityGraphics: entitiesGraphics){
            entityGraphics.dispose();
        }
        level.dispose();
        batch.dispose();
    }

    public TileMovement getTileMovement() {
        return tileMovement;
    }


    public TiledMapTileLayer getGroundLayer() {
        return groundLayer;
    }

}
