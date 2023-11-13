package ru.platformer.game.graphics.graphicsObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import ru.platformer.game.graphics.GameObjectGraphics;
import ru.platformer.game.model.Explosion;
import ru.platformer.game.model.objects.Obstacle;
import ru.platformer.util.GdxGameUtils;

public class ExplosionGraphics implements GameObjectGraphics {

    private final Texture texture;
    private final TextureRegion textureRegion;
    private final Rectangle rectangle;
    private final Explosion explosion;

    public ExplosionGraphics(
            String fileNameTexture, Explosion explosion, TiledMapTileLayer groundLayer) {
        texture = new Texture(fileNameTexture);
        textureRegion = new TextureRegion(texture);
        rectangle = GdxGameUtils.createBoundingRectangle(textureRegion);
        this.explosion = explosion;
        GdxGameUtils.moveRectangleAtTileCenter(
                groundLayer,
                rectangle,
                explosion.getCurrentCoordinates()
        );
    }

    public void draw(Batch batch){
        GdxGameUtils.drawTextureRegionUnscaled(batch, textureRegion, rectangle, 0f);
    }

    @Override
    public Rectangle getRectangle() {
        return rectangle;
    }

    public void dispose(){
        texture.dispose();
    }
}
