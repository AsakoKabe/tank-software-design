package ru.mipt.bit.platformer.action;

import ru.mipt.bit.platformer.gameEntities.GameEntity;

import java.util.ArrayList;

public interface EntityActionController {
    public ArrayList<Action> generateActions();
    public void addGameEntity(GameEntity gameEntity);
}
