package ru.mipt.bit.platformer.gameEntities;

import com.badlogic.gdx.math.GridPoint2;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private final List<GameEntity> gameEntities;

    public Level() {
        gameEntities = new ArrayList<>();
    }

    public void addGameEntity(GameEntity gameEntity){
        gameEntities.add(gameEntity);
    }

    public void updateState(float deltaTime) {
        for (GameEntity gameEntity: gameEntities){
            gameEntity.updateState(deltaTime);
        }
    }

    public boolean collisionExist(GridPoint2 coordinates) {
        for (GameEntity gameEntity: gameEntities){
            if (gameEntity.getCurrentCoordinates().equals(coordinates)){
                return true;
            }
        }
        return false;
    }
}
