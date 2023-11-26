package ru.platformer.game.graphics.graphicsObjects.creationStategies;

import ru.platformer.game.GameObject;
import ru.platformer.game.graphics.GameObjectGraphics;
import ru.platformer.game.graphics.GraphicsFactory;
import ru.platformer.game.graphics.graphicsObjects.BulletGraphics;
import ru.platformer.game.model.objects.Bullet;
import ru.platformer.util.TileMovement;

public class BulletGraphicsFactory implements GraphicsFactory {
    private final String fileTexture;
    private final TileMovement tileMovement;

    public BulletGraphicsFactory(String fileTexture, TileMovement tileMovement) {
        this.fileTexture = fileTexture;
        this.tileMovement = tileMovement;
    }

    @Override
    public GameObjectGraphics create(GameObject gameObject) {
        return new BulletGraphics(
                fileTexture,
                (Bullet) gameObject,
                tileMovement
        );
    }
}
