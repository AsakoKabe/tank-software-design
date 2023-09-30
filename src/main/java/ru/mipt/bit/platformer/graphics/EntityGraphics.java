package ru.mipt.bit.platformer.graphics;

import com.badlogic.gdx.graphics.g2d.Batch;
import ru.mipt.bit.platformer.util.TileMovement;

public interface EntityGraphics {
    public void dispose();
    public void drawTexture(Batch batch);
    public void update(TileMovement tileMovement);

}
