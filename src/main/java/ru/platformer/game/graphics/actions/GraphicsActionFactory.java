package ru.platformer.game.graphics.actions;

import ru.platformer.game.Action;
import ru.platformer.game.graphics.LevelGraphics;

public interface GraphicsActionFactory {
    Action create(LevelGraphics levelGraphics);
}
