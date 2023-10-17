package ru.platformer.game.model;

import com.badlogic.gdx.math.GridPoint2;
import ru.platformer.game.GameObject;

import java.util.HashSet;
import java.util.Set;

public class CollisionDetector implements LevelListener{
    private final Set<GridPoint2> usedCoordinates = new HashSet<>();

    public void addCoordinates(GridPoint2 gridPoint2){
        usedCoordinates.add(gridPoint2);
    }

    public void removeCoordinates(GridPoint2 gridPoint2){
        usedCoordinates.remove(gridPoint2);
    }


    public boolean collisionExist(GridPoint2 coordinates) {
        return usedCoordinates.contains(coordinates);
    }

    @Override
    public void onAddGameObject(GameObject gameObject) {
        addCoordinates(gameObject.getCurrentCoordinates());
    }
}
