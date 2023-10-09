package ru.platformer.game.entityControllers;

import ru.platformer.game.Action;
import ru.platformer.game.GameEntity;

import java.util.ArrayList;

public interface EntityController {
    public ArrayList<Action> generateActions();
    public void addGameEntity(GameEntity gameEntity);
}
