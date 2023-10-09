package ru.platformer.game.entityControllers;

import ru.platformer.game.Action;
import ru.platformer.game.GameEntity;

import java.util.ArrayList;

public class AIController implements EntityController{
    @Override
    public ArrayList<Action> generateActions() {
        return new ArrayList<>();
    }

    @Override
    public void addGameEntity(GameEntity gameEntity) {

    }
}
