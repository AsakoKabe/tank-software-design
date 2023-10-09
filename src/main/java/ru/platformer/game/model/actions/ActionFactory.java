package ru.platformer.game.model.actions;

import ru.platformer.game.Action;
import ru.platformer.game.GameEntity;

public interface ActionFactory {
    Action create(GameEntity gameEntity);
}
