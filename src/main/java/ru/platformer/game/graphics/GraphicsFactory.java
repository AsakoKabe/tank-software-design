package ru.platformer.game.graphics;

import ru.platformer.game.GameObject;

public interface GraphicsFactory {
    GameObjectGraphics create(GameObject gameObject);
}
