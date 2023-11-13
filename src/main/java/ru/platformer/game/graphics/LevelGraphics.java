package ru.platformer.game.graphics;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import ru.platformer.game.GameObject;
import ru.platformer.game.model.LevelListener;
import ru.platformer.util.TileMovement;

import java.util.Map;

public interface LevelGraphics extends LevelListener {
    void addGraphicsStrategyMapping(
            Class<? extends GameObject> clazz,
            GraphicsStrategy graphicsStrategy
    );


    void render();


    void dispose();

    Map<GameObject, GameObjectGraphics> getGraphicsByGameObject();

    Batch getBatch();
}
