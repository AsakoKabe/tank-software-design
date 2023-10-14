package ru.platformer.game.model;

import com.badlogic.gdx.math.GridPoint2;
import ru.platformer.game.GameObject;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private final List<GameObject> gameEntities;

    public Level() {
        gameEntities = new ArrayList<>();
    }

    public void addGameEntity(GameObject gameEntity){
        gameEntities.add(gameEntity);
    }

    public void updateState(float deltaTime) {
        for (GameObject gameEntity: gameEntities){
            gameEntity.updateState(deltaTime);
        }
    }

    public boolean collisionExist(GridPoint2 coordinates) {
        for (GameObject gameEntity: gameEntities){
            if (gameEntity.getCurrentCoordinates().equals(coordinates)){
                return true;
            }
        }
        return false;
    }
}
