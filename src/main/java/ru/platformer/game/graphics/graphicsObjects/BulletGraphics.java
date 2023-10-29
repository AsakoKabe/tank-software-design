package ru.platformer.game.graphics.graphicsObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import ru.platformer.game.graphics.GameObjectGraphics;
import ru.platformer.game.model.objects.Bullet;
import ru.platformer.util.GdxGameUtils;
import ru.platformer.util.TileMovement;

public class BulletGraphics implements GameObjectGraphics {

    private final Texture texture;
    private final TextureRegion textureRegion;
    private final Rectangle rectangle;
    private final Bullet bullet;
    private final TileMovement tileMovement;

    public BulletGraphics(String fileNameTexture, Bullet bullet, TileMovement tileMovement) {
        texture = new Texture(fileNameTexture);
        textureRegion = new TextureRegion(texture);
        rectangle = GdxGameUtils.createBoundingRectangle(textureRegion);
        this.bullet = bullet;
        this.tileMovement = tileMovement;
    }

    public void draw(Batch batch){
        tileMovement.moveRectangleBetweenTileCenters(
                rectangle,
                bullet.getCurrentCoordinates(),
                bullet.getDestinationCoordinates(),
                bullet.getMovementProgress()
        );
        GdxGameUtils.drawTextureRegionUnscaled(batch, textureRegion, rectangle, 0f);
    }

    public void dispose(){
        texture.dispose();
    }
}
