package ru.platformer.game.model.actions;

import ru.platformer.game.Action;
import ru.platformer.game.GameEntity;

import java.util.ArrayList;

public interface EntityController {
    public ArrayList<Action> generateActions();
    public void addGameEntity(GameEntity gameEntity);
}
