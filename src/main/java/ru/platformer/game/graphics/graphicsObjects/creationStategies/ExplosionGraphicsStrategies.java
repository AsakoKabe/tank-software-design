package ru.platformer.game.graphics.graphicsObjects.creationStategies;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import ru.platformer.game.GameObject;
import ru.platformer.game.graphics.GameObjectGraphics;
import ru.platformer.game.graphics.GraphicsFactory;
import ru.platformer.game.graphics.graphicsObjects.ExplosionGraphics;
import ru.platformer.game.model.Explosion;

public class ExplosionGraphicsStrategies implements GraphicsFactory {
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
