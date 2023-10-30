package ru.platformer.game.model;

import com.badlogic.gdx.math.GridPoint2;
import ru.platformer.game.GameObject;

import java.util.*;

public class CollisionDetector implements LevelListener {
    private final List<GameObject> gameObjects = new ArrayList<>();

    @Override
    public void onAddGameObject(GameObject gameObject) {
        gameObjects.add(gameObject);
    }

    @Override
    public void onDeleteGameObject(GameObject gameObject) {
        gameObjects.remove(gameObject);

    }

    public boolean collisionExist(GridPoint2 coordinates) {
        for (GameObject gameObject: gameObjects){
            if (gameObject.isBusyCoordinate(coordinates)){
                return true;
            }
        }
        return false;
    }

    public GameObject getObjectByCoordinates(GridPoint2 coordinates) {
        for (GameObject gameObject: gameObjects){
            if (gameObject.isBusyCoordinate(coordinates)){
                return gameObject;
            }
        }
        return null;
    }
}
