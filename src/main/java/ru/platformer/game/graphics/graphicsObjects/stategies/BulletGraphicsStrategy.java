package ru.platformer.game.graphics.graphicsObjects.stategies;

import ru.platformer.game.GameObject;
import ru.platformer.game.graphics.GameObjectGraphics;
import ru.platformer.game.graphics.GraphicsStrategy;
import ru.platformer.game.graphics.graphicsObjects.BulletGraphics;
import ru.platformer.game.model.objects.Bullet;
import ru.platformer.util.TileMovement;

public class BulletGraphicsStrategy implements GraphicsStrategy {
    private final String fileTexture;
    private final TileMovement tileMovement;

    public BulletGraphicsStrategy(String fileTexture, TileMovement tileMovement) {
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
