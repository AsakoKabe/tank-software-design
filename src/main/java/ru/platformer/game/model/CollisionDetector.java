package ru.platformer.game.model;

import com.badlogic.gdx.math.GridPoint2;
import ru.platformer.game.GameObject;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CollisionDetector implements LevelListener {
    private final Map<GridPoint2, GameObject> usedCoordinates = new HashMap<>();

    public void addGameObjectByCoordinates(GameObject gameObject, GridPoint2 gridPoint2){
        usedCoordinates.put(gridPoint2, gameObject);
    }

    public void removeCoordinates(GridPoint2 gridPoint2){
        usedCoordinates.remove(gridPoint2);
    }

    public boolean collisionExist(GridPoint2 coordinates) {
        return usedCoordinates.containsKey(coordinates);
    }

    public GameObject getObjectByCoordinates(GridPoint2 coordinates){
        return usedCoordinates.getOrDefault(coordinates, null);
    }

    @Override
    public void onAddGameObject(GameObject gameObject) {
        addGameObjectByCoordinates(gameObject, gameObject.getCurrentCoordinates());
    }

    @Override
    public void onDeleteGameObject(GameObject gameObject) {
        System.out.println("deleted object for cd " + gameObject.toString());
        System.out.println("coord " + gameObject.getCurrentCoordinates().toString());
        System.out.println("size before delete " + usedCoordinates.size());
        Set<GridPoint2> deletedCoordinates = new HashSet<>();
        usedCoordinates.keySet().forEach(coordinates -> {
            if (usedCoordinates.get(coordinates) == gameObject){
                deletedCoordinates.add(coordinates);
            }
        });

        usedCoordinates.keySet().forEach(coordinates -> {
            if (usedCoordinates.get(coordinates) != null){
                System.out.println(usedCoordinates.get(coordinates).toString());
            }
        });

        for (GridPoint2 gridPoint2: deletedCoordinates){
            usedCoordinates.remove(gridPoint2);
        }
        System.out.println("size after delete " + usedCoordinates.size());
//        usedCoordinates.remove(gameObject.getCurrentCoordinates());
    }
}
