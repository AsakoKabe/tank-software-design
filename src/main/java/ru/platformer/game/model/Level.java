package ru.platformer.game.model;

import ru.platformer.game.GameObject;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private final List<GameObject> gameObjects;
    private final ArrayList<LevelListener> levelListeners;
    private final int height;

    private final int width;

    public Level(
            ArrayList<LevelListener> levelListeners,
            int height,
            int width
    ) {
        this.height = height;
        this.width = width;
        gameObjects = new ArrayList<>();
        this.levelListeners = levelListeners;
    }


    public void addGameObject(GameObject gameObject){
        gameObjects.add(gameObject);
        levelListeners.forEach(levelListener -> levelListener.onAddGameObject(gameObject));
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
