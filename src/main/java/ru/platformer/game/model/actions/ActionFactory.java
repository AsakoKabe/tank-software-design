package ru.platformer.game.model.actions;

import ru.platformer.game.Action;
import ru.platformer.game.GameObject;

public interface ActionFactory {
    Action create(GameObject gameObject);
}
