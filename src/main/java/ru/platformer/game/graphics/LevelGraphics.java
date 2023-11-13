package ru.platformer.game.graphics;

import com.badlogic.gdx.graphics.g2d.Batch;
import ru.platformer.game.GameObject;
import ru.platformer.game.model.LevelListener;

import java.util.Map;

public interface LevelGraphics extends LevelListener {
    void addGraphicsStrategyMapping(
            Class<? extends GameObject> clazz,
            GraphicsFactory graphicsFactory
    );


    void render();


    void dispose();

    Map<GameObject, GameObjectGraphics> getGraphicsByGameObject();

    Batch getBatch();
}
