package ru.platformer.game.graphics;

import ru.platformer.game.GameObject;

public interface GraphicsStrategy {
    GameObjectGraphics create(GameObject gameObject);
}
