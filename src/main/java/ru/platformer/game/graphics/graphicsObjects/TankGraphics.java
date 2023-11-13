package ru.platformer.game.graphics.graphicsObjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import ru.platformer.game.graphics.GameObjectGraphics;
import ru.platformer.game.model.Damaged;
import ru.platformer.game.model.Health;
import ru.platformer.game.model.objects.Tank;
import ru.platformer.util.GdxGameUtils;
import ru.platformer.util.TileMovement;

public class TankGraphics implements GameObjectGraphics {
    private final Texture texture;
    private final TextureRegion textureRegion;
    private final Rectangle rectangle;
    private final Tank tank;
    private final TileMovement tileMovement;

    public TankGraphics(String fileNameTexture, Tank tank, TileMovement tileMovement) {
        texture = new Texture(fileNameTexture);
        textureRegion = new TextureRegion(texture);
        rectangle = GdxGameUtils.createBoundingRectangle(textureRegion);
        this.tank = tank;
        this.tileMovement = tileMovement;
    }

    public void draw(Batch batch) {
        tileMovement.moveRectangleBetweenTileCenters(
                rectangle,
                tank.getCurrentCoordinates(),
                tank.getDestinationCoordinates(),
                tank.getMovementProgress()
        );
        GdxGameUtils.drawTextureRegionUnscaled(batch, textureRegion, rectangle, tank.getRotation());
    }

    @Override
    public Rectangle getRectangle() {
        return rectangle;
    }

    public void dispose() {
        texture.dispose();
    }

}
