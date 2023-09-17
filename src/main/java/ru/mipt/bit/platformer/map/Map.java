package ru.mipt.bit.platformer.map;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Interpolation;
import ru.mipt.bit.platformer.gameEntity.Player;
import ru.mipt.bit.platformer.util.TileMovement;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createSingleLayerMapRenderer;
import static ru.mipt.bit.platformer.util.GdxGameUtils.getSingleLayer;

public abstract class Map {
    private final TiledMap level;
    private final MapRenderer levelRenderer;
    private final TileMovement tileMovement;

    private final TiledMapTileLayer groundLayer;


    public Map(String fileNameMap, Batch batch) {
        level = new TmxMapLoader().load(fileNameMap);
        levelRenderer = createSingleLayerMapRenderer(level, batch);
        groundLayer = getSingleLayer(level);
        tileMovement = new TileMovement(groundLayer, Interpolation.smooth);
    }

    public void levelRender() {
        levelRenderer.render();
    }

    public void levelDispose() {
        level.dispose();
    }

    public void calculateInterpolatedPlayerScreenCoordinates(
            Player player
    ) {
        player.setRectangle(
                tileMovement.moveRectangleBetweenTileCenters(
                        player.getRectangle(),
                        player.getCurrentCoordinates(),
                        player.getDestinationCoordinates(),
                        player.getMovementProgress()
                )
        );

    }

    public TiledMapTileLayer getGroundLayer() {
        return groundLayer;
    }

}
