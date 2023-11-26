package ru.platformer.game.model.actions;

import ru.platformer.game.Action;
import ru.platformer.game.GameObject;

public interface ActionFactory<T> {
    Action create(T gameObject);
}
