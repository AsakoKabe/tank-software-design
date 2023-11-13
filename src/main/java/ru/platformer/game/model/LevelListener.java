package ru.platformer.game.model;

import ru.platformer.game.GameObject;

public interface LevelListener {
    void onAddGameObject(GameObject gameObject);
    void onDeleteGameObject(GameObject gameObject);
}
