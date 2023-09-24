package ru.mipt.bit.platformer.graphics;

import com.badlogic.gdx.graphics.g2d.Batch;

public interface Graphics {
    public void dispose();
    public void drawTexture(Batch batch);
}
