package ru.platformer.game.graphics;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;

public interface GameObjectGraphics {
    void dispose();
    void draw(Batch batch);

    Rectangle getRectangle();

}
