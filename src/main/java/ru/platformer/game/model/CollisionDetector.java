package ru.platformer.game.model;

import com.badlogic.gdx.math.GridPoint2;
import ru.platformer.game.GameObject;

import java.util.*;

public class CollisionDetector implements LevelListener {
    private final List<Colliding> collidings = new ArrayList<>();

    @Override
    public void onAddGameObject(GameObject gameObject) {
        if (gameObject instanceof Colliding){
            collidings.add((Colliding) gameObject);
        }
    }

    @Override
    public void onDeleteGameObject(GameObject gameObject) {
        if (gameObject instanceof Colliding){
            collidings.remove((Colliding) gameObject);
        }

    }

    public boolean collisionExist(GridPoint2 coordinates) {
        for (Colliding colliding: collidings){
            if (colliding.isBusyCoordinate(coordinates)){
                return true;
            }
        }
        return false;
    }

    public Colliding getObjectByCoordinates(GridPoint2 coordinates) {
        for (Colliding colliding: collidings){
            if (colliding.isBusyCoordinate(coordinates)){
                return colliding;
            }
        }
        return null;
    }
}
