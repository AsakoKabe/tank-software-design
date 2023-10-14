package ru.platformer.game.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import ru.platformer.game.model.Tank;
import ru.platformer.util.TileMovement;
import ru.platformer.util.GdxGameUtils;

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

    public void draw(Batch batch){
        tileMovement.moveRectangleBetweenTileCenters(
                rectangle,
                tank.getCurrentCoordinates(),
                tank.getDestinationCoordinates(),
                tank.getMovementProgress()
        );
        GdxGameUtils.drawTextureRegionUnscaled(batch, textureRegion, rectangle, tank.getRotation());
    }

    public void dispose(){
        texture.dispose();
    }

}
