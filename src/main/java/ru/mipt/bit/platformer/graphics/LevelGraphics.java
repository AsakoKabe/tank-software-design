package ru.mipt.bit.platformer.graphics;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Interpolation;
import ru.mipt.bit.platformer.util.TileMovement;

import java.util.ArrayList;
import java.util.List;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createSingleLayerMapRenderer;
import static ru.mipt.bit.platformer.util.GdxGameUtils.getSingleLayer;

public class LevelGraphics {
    private TiledMap level;
    private MapRenderer levelRenderer;
    private TileMovement tileMovement;

    private TiledMapTileLayer groundLayer;
    private Batch batch;

    private List<EntityGraphics> entitiesGraphics;


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
        updateEntitiesGraphics();

        // render each tile of the level
        levelRenderer.render();

        // start recording all drawing commands
        batch.begin();

        for (EntityGraphics entityGraphics: entitiesGraphics){
            entityGraphics.drawTexture(batch);
        }
        // submit all drawing requests
        batch.end();
    }

    private void updateEntitiesGraphics() {
        for (EntityGraphics entityGraphics: entitiesGraphics){
            entityGraphics.update(tileMovement);
        }
    }

    public void dispose() {
        // dispose of all the native resources (classes which implement com.badlogic.gdx.utils.Disposable)
        for (EntityGraphics entityGraphics: entitiesGraphics){
            entityGraphics.dispose();
        }
        level.dispose();
        batch.dispose();
    }
}
