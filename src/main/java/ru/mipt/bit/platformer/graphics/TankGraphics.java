package ru.mipt.bit.platformer.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.gameEntities.GameEntity;
import ru.mipt.bit.platformer.gameEntities.Tank;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createBoundingRectangle;
import static ru.mipt.bit.platformer.util.GdxGameUtils.drawTextureRegionUnscaled;

public class TankGraphics implements Graphics{
    private final Texture texture;
    private final TextureRegion textureRegion;
    private final Rectangle rectangle;
    private final Tank tank;

    public TankGraphics(String fileNameTexture, Tank tank) {
        texture = new Texture(fileNameTexture);
        textureRegion = new TextureRegion(texture);
        rectangle = createBoundingRectangle(textureRegion);
        this.tank = tank;

    }

    public void drawTexture(Batch batch){
        drawTextureRegionUnscaled(batch, textureRegion, rectangle, tank.getRotation());
    }

    public void dispose(){
        texture.dispose();
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
