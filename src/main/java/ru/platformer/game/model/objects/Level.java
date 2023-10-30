package ru.platformer.game.model.objects;

import ru.platformer.game.GameObject;
import ru.platformer.game.model.LevelListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Level {
    private final Set<GameObject> gameObjects;
    private final List<LevelListener> levelListeners;
    private final int height;

    private final int width;

    public Level(
            List<LevelListener> levelListeners,
            int height,
            int width
    ) {
        this.height = height;
        this.width = width;
        gameObjects = new HashSet<>();
        this.levelListeners = levelListeners;
    }


    public void addGameObject(GameObject gameObject){
        gameObjects.add(gameObject);
        levelListeners.forEach(levelListener -> levelListener.onAddGameObject(gameObject));
    }

    public void deleteGameObject(GameObject gameObject){
        gameObjects.remove(gameObject);
        levelListeners.forEach(levelListener -> levelListener.onDeleteGameObject(gameObject));
    }

    public void updateState(float deltaTime) {
        for (GameObject gameObject: gameObjects){
            gameObject.updateState(deltaTime);
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }


}
