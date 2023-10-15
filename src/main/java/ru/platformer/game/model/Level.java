package ru.platformer.game.model;

import com.badlogic.gdx.math.GridPoint2;
import ru.platformer.game.GameObject;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private final List<GameObject> gameObjects;
    private final ArrayList<LevelListener> levelListeners;

    public Level(ArrayList<LevelListener> levelListeners) {
        gameObjects = new ArrayList<>();
        this.levelListeners = levelListeners;
    }

    public void addGameObject(GameObject gameObject){
        gameObjects.add(gameObject);
        levelListeners.forEach(levelListener -> levelListener.onAddGameObject(gameObject));
    }

    public void updateState(float deltaTime) {
        for (GameObject gameEntity: gameObjects){
            gameEntity.updateState(deltaTime);
        }
    }

    public boolean collisionExist(GridPoint2 coordinates) {
        for (GameObject gameEntity: gameObjects){
            if (gameEntity.getCurrentCoordinates().equals(coordinates)){
                return true;
            }
        }
        return false;
    }

}
