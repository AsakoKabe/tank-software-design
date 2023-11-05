package ru.platformer.game.graphics;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.math.Rectangle;
import ru.platformer.game.GameObject;
import ru.platformer.game.model.Health;
import ru.platformer.util.GdxGameUtils;

import java.util.Map;

public class LevelGraphicsHealthBarDecorator extends LevelGraphicsDecorator {

    private final Batch batch;
    private final Map<GameObject, GameObjectGraphics> graphicsByGameObject;

    public LevelGraphicsHealthBarDecorator(LevelGraphics levelGraphics) {
        super(levelGraphics);
        this.batch = getBatch();
        this.graphicsByGameObject = getGraphicsByGameObject();
    }

    private static TextureRegion createBar(int health, Color color) {
        Pixmap pixmap = new Pixmap(90 * health / 100, 20, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fillRectangle(0, 0, 90 * health / 100, 20);
        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        TextureRegion healthBar = new TextureRegion(texture);
        return healthBar;
    }

    private static boolean GameObjectHasHealth(GameObject gameObject) {
        return gameObject instanceof Health;
    }

    @Override
    public void render() {
        levelGraphics.render();
        batch.begin();
        graphicsByGameObject.forEach((key, value) -> {
            if (GameObjectHasHealth(key)) {
                drawHealthBar(getBatch(), value.getRectangle(), ((Health) key).getHealth());
            }
            value.draw(getBatch());
        });
        batch.end();
    }


    private void drawHealthBar(Batch batch, Rectangle rectangle, int health) {
        if (alreadyDead(health)) return;

        TextureRegion healthBgBar = createBar(100, Color.RED);
        TextureRegion healthLeftBar = createBar(health, Color.GREEN);
        Rectangle hpRectangle = new Rectangle(rectangle);
        hpRectangle.y += 90;
        GdxGameUtils.drawTextureRegionUnscaled(batch, healthBgBar, hpRectangle, 0);
        GdxGameUtils.drawTextureRegionUnscaled(batch, healthLeftBar, hpRectangle, 0);
    }

    private static boolean alreadyDead(int health) {
        return health == 0;
    }

}
