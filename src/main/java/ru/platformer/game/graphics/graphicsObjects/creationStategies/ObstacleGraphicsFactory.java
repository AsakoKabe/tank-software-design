package ru.platformer.game.graphics.graphicsObjects.creationStategies;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import ru.platformer.game.GameObject;
import ru.platformer.game.graphics.GameObjectGraphics;
import ru.platformer.game.graphics.GraphicsFactory;
import ru.platformer.game.graphics.graphicsObjects.ObstacleGraphics;
import ru.platformer.game.model.objects.Obstacle;

public class ObstacleGraphicsFactory implements GraphicsFactory {
    private final String fileTexture;
    private final TiledMapTileLayer tiledMapTileLayer;

    public ObstacleGraphicsFactory(String fileTexture, TiledMapTileLayer tiledMapTileLayer) {
        this.fileTexture = fileTexture;
        this.tiledMapTileLayer = tiledMapTileLayer;
    }

    @Override
    public GameObjectGraphics create(GameObject gameObject) {
        return new ObstacleGraphics(
                fileTexture,
                (Obstacle) gameObject,
                tiledMapTileLayer
        );
    }
}
