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

    public boolean collidesExist(GridPoint2 coordinates) {
        System.out.println("coordinates");
        System.out.println(coordinates.toString());

        for (GameEntity gameEntity: gameEntities){
            System.out.println("ge");
            System.out.println(gameEntity.getCurrentCoordinates().toString());
            if (gameEntity.getCurrentCoordinates().equals(coordinates)){
                System.out.println("return true");
                return true;
            }
        }
        return false;
    }
}
