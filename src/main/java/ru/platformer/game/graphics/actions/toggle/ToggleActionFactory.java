package ru.platformer.game.graphics.actions.toggle;

import ru.platformer.game.Action;
import ru.platformer.game.graphics.Toggle;
import ru.platformer.game.graphics.actions.GraphicsActionFactory;
import ru.platformer.game.graphics.LevelGraphics;

public class ToggleActionFactory implements GraphicsActionFactory {
    @Override
    public Action create(LevelGraphics levelGraphics) {
        return new ToggleAction((Toggle) levelGraphics);
    }
}
