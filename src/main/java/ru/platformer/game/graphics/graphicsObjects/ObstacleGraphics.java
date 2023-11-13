package ru.platformer.game.graphics.graphicsObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import ru.platformer.game.graphics.GameObjectGraphics;
import ru.platformer.game.model.objects.Obstacle;
import ru.platformer.util.GdxGameUtils;

public class ObstacleGraphics implements GameObjectGraphics {
    private final Texture texture;
    private final TextureRegion textureRegion;
    private final Rectangle rectangle;
    private final Obstacle obstacle;

    public ObstacleGraphics(String fileNameTexture, Obstacle obstacle, TiledMapTileLayer groundLayer) {
        texture = new Texture(fileNameTexture);
        textureRegion = new TextureRegion(texture);
        rectangle = GdxGameUtils.createBoundingRectangle(textureRegion);
        this.obstacle = obstacle;
        GdxGameUtils.moveRectangleAtTileCenter(
                groundLayer,
                rectangle,
                obstacle.getCurrentCoordinates()
        );

    }

    public void draw(Batch batch){
        GdxGameUtils.drawTextureRegionUnscaled(batch, textureRegion, rectangle, 0f);
    }

    public void dispose(){
        texture.dispose();
    }
}
