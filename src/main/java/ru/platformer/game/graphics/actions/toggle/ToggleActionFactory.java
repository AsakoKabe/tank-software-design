package ru.platformer.game.graphics.actions.toggle;

import ru.platformer.game.Action;
import ru.platformer.game.graphics.Toggle;
import ru.platformer.game.graphics.LevelGraphics;
import ru.platformer.game.model.actions.ActionFactory;

public class ToggleActionFactory implements ActionFactory<LevelGraphics> {
    @Override
    public Action create(LevelGraphics levelGraphics) {
        return new ToggleAction((Toggle) levelGraphics);
    }
}
