package ru.platformer.game.graphics.graphicsObjects.creationStategies;

import ru.platformer.game.GameObject;
import ru.platformer.game.graphics.GameObjectGraphics;
import ru.platformer.game.graphics.GraphicsFactory;
import ru.platformer.game.graphics.graphicsObjects.TankGraphics;
import ru.platformer.game.model.objects.Tank;
import ru.platformer.util.TileMovement;

public class TankGraphicsFactory implements GraphicsFactory {
    private final String fileTexture;
    private final TileMovement tileMovement;

    public TankGraphicsFactory(String fileTexture, TileMovement tileMovement) {
        this.fileTexture = fileTexture;
        this.tileMovement = tileMovement;
    }

    @Override
    public GameObjectGraphics create(GameObject gameObject) {
        return new TankGraphics(
                fileTexture,
                (Tank) gameObject,
                tileMovement
        );
    }
}
