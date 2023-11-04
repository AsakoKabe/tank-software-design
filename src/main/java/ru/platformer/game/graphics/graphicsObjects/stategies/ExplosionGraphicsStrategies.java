package ru.platformer.game.graphics.graphicsObjects.stategies;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import ru.platformer.game.GameObject;
import ru.platformer.game.graphics.GameObjectGraphics;
import ru.platformer.game.graphics.GraphicsStrategy;
import ru.platformer.game.graphics.graphicsObjects.ExplosionGraphics;
import ru.platformer.game.graphics.graphicsObjects.ObstacleGraphics;
import ru.platformer.game.model.Explosion;
import ru.platformer.game.model.objects.Obstacle;

public class ExplosionGraphicsStrategies implements GraphicsStrategy {
    private final String fileTexture;
    private final TiledMapTileLayer tiledMapTileLayer;

    public ExplosionGraphicsStrategies(String fileTexture, TiledMapTileLayer tiledMapTileLayer) {
        this.fileTexture = fileTexture;
        this.tiledMapTileLayer = tiledMapTileLayer;
    }

    @Override
    public GameObjectGraphics create(GameObject gameObject) {
        return new ExplosionGraphics(
                fileTexture,
                (Explosion) gameObject,
                tiledMapTileLayer
        );
    }
}
