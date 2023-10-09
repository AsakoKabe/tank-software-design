package ru.platformer.game.model.entityFiller;

import ru.platformer.game.graphics.LevelGraphics;
import ru.platformer.game.model.Level;
import ru.platformer.game.entityControllers.AIController;
import ru.platformer.game.entityControllers.PlayerController;

public class Filler {
    private final FillingStrategy fillingStrategy;
    private final Level level;
    private final LevelGraphics levelGraphics;
    private final PlayerController playerController;
    private final AIController aiController;

    public Filler(
            FillingStrategy fillingStrategy,
            Level level,
            LevelGraphics levelGraphics,
            PlayerController playerController,
            AIController aiController
    ) {
        this.fillingStrategy = fillingStrategy;
        this.level = level;
        this.levelGraphics = levelGraphics;
        this.playerController = playerController;
        this.aiController = aiController;
    }

    public void filling(){
        fillingStrategy.createEntities(
                level,
                levelGraphics,
                playerController,
                aiController
        );
    }
}
