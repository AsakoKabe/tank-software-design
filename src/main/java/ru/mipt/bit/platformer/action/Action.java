package ru.mipt.bit.platformer.action;

import ru.mipt.bit.platformer.gameEntities.GameEntity;

public interface Action {
    void apply(GameEntity gameEntity);
}
