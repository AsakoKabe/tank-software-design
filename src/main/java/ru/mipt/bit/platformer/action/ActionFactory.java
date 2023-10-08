package ru.mipt.bit.platformer.action;

import ru.mipt.bit.platformer.gameEntities.GameEntity;

public interface ActionFactory {
    Action create(GameEntity gameEntity);
}
