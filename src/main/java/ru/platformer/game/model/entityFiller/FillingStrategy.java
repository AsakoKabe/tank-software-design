package ru.platformer.game.model.entityFiller;

import ru.platformer.game.graphics.LevelGraphics;
import ru.platformer.game.model.Level;
import ru.platformer.game.entityControllers.AIController;
import ru.platformer.game.entityControllers.PlayerController;

public interface FillingStrategy {
    void createEntities(
            Level level,
            LevelGraphics levelGraphics,
            PlayerController playerController,
            AIController aiController
    );
}
