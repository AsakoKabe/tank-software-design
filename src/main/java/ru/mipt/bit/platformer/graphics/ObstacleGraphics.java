package ru.mipt.bit.platformer.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.gameEntities.Obstacle;
import ru.mipt.bit.platformer.util.TileMovement;

import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class ObstacleGraphics implements EntityGraphics {
    private final Texture texture;
    private final TextureRegion textureRegion;
    private final Rectangle rectangle;
    private final Obstacle obstacle;

    public ObstacleGraphics(String fileNameTexture, Obstacle obstacle) {
        texture = new Texture(fileNameTexture);
        textureRegion = new TextureRegion(texture);
        rectangle = createBoundingRectangle(textureRegion);
        this.obstacle = obstacle;
//        moveRectangleAtTileCenter(
//                groundLayer,
//                rectangle,
//                obstacle.getCurrentCoordinates()
//        );

    }

    public void drawTexture(Batch batch){
        drawTextureRegionUnscaled(batch, textureRegion, rectangle, 0f);
    }

    @Override
    public void update(TileMovement tileMovement) {
    }

    public void dispose(){
        texture.dispose();
    }
}
