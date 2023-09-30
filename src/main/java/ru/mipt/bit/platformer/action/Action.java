package ru.mipt.bit.platformer.action;

import ru.mipt.bit.platformer.gameEntities.GameEntity;

public interface Action {
    public void apply(GameEntity gameEntity);
}
