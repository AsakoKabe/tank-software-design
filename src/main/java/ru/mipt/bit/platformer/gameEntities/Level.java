package ru.mipt.bit.platformer.gameEntities;

import ru.mipt.bit.platformer.action.Direction;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private List<GameEntity> gameEntities;

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

}
