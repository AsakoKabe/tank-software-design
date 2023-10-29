package ru.platformer.game.graphics.graphicsObjects.stategies;

import ru.platformer.game.GameObject;
import ru.platformer.game.graphics.GameObjectGraphics;
import ru.platformer.game.graphics.GraphicsStrategy;
import ru.platformer.game.graphics.graphicsObjects.TankGraphics;
import ru.platformer.game.model.Tank;
import ru.platformer.util.TileMovement;

public class TankGraphicsStrategy implements GraphicsStrategy {
    private final String fileTexture;
    private final TileMovement tileMovement;

    public TankGraphicsStrategy(String fileTexture, TileMovement tileMovement) {
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
