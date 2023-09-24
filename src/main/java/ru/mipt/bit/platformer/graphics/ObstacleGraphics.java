package ru.mipt.bit.platformer.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.gameEntities.Obstacle;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createBoundingRectangle;
import static ru.mipt.bit.platformer.util.GdxGameUtils.drawTextureRegionUnscaled;

public class ObstacleGraphics implements Graphics{
    private final Texture texture;
    private final TextureRegion textureRegion;
    private final Rectangle rectangle;
    private final Obstacle obstacle;

    public ObstacleGraphics(String fileNameTexture, Obstacle obstacle) {
        texture = new Texture(fileNameTexture);
        textureRegion = new TextureRegion(texture);
        rectangle = createBoundingRectangle(textureRegion);
        this.obstacle = obstacle;

    }

    public void drawTexture(Batch batch){
        drawTextureRegionUnscaled(batch, textureRegion, rectangle, 0f);
    }

    public void dispose(){
        texture.dispose();
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
