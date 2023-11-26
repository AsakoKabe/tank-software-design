package ru.platformer.game.graphics.decorators;

import com.badlogic.gdx.graphics.g2d.Batch;
import ru.platformer.game.GameObject;
import ru.platformer.game.graphics.GameObjectGraphics;
import ru.platformer.game.graphics.GraphicsFactory;
import ru.platformer.game.graphics.LevelGraphics;

import java.util.Map;

public abstract class LevelGraphicsDecorator implements LevelGraphics {
    protected final LevelGraphics levelGraphics;

    public LevelGraphicsDecorator(LevelGraphics levelGraphics) {
        this.levelGraphics = levelGraphics;
    }

    @Override
    public void addGraphicsStrategyMapping(
            Class<? extends GameObject> clazz,
            GraphicsFactory graphicsFactory
    ){
        levelGraphics.addGraphicsStrategyMapping(clazz, graphicsFactory);
    }

    @Override
    public void render() {
        levelGraphics.render();
    }


    @Override
    public void dispose() {
        levelGraphics.dispose();
    }

    @Override
    public Map<GameObject, GameObjectGraphics> getGraphicsByGameObject() {
        return levelGraphics.getGraphicsByGameObject();
    }

    @Override
    public Batch getBatch() {
        return levelGraphics.getBatch();
    }


    @Override
    public void onAddGameObject(GameObject gameObject) {
        levelGraphics.onAddGameObject(gameObject);
    }

    @Override
    public void onDeleteGameObject(GameObject gameObject) {
        levelGraphics.onDeleteGameObject(gameObject);
    }
}
