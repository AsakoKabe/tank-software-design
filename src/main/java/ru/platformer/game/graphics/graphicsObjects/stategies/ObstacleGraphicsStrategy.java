package ru.platformer.game.graphics.graphicsObjects.stategies;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import ru.platformer.game.GameObject;
import ru.platformer.game.graphics.GameObjectGraphics;
import ru.platformer.game.graphics.GraphicsStrategy;
import ru.platformer.game.graphics.graphicsObjects.ObstacleGraphics;
import ru.platformer.game.model.objects.Obstacle;

public class ObstacleGraphicsStrategy implements GraphicsStrategy {
    private final String fileTexture;
    private final TiledMapTileLayer tiledMapTileLayer;

    public ObstacleGraphicsStrategy(String fileTexture, TiledMapTileLayer tiledMapTileLayer) {
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
