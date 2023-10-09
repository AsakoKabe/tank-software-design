package ru.platformer;

import ru.platformer.game.Direction;
import ru.platformer.game.entityControllers.AIController;
import ru.platformer.game.graphics.LevelGraphics;
import ru.platformer.game.model.*;
import ru.platformer.game.entityControllers.PlayerController;
import ru.platformer.game.model.actions.ActionManager;
import ru.platformer.game.model.actions.move.MoveFactory;
import ru.platformer.game.model.entityFiller.FileFilling;
import ru.platformer.game.model.entityFiller.Filler;
import ru.platformer.game.model.entityFiller.RandomFilling;

import java.util.List;

import static com.badlogic.gdx.Input.Keys.*;
import static com.badlogic.gdx.Input.Keys.D;

public class Initializer {
    public static void initKeyBoardMappings(PlayerController playerController, Level level) {
        playerController.addKeyActionFactoryMapping(UP, new MoveFactory(Direction.UP, level));
        playerController.addKeyActionFactoryMapping(W, new MoveFactory(Direction.UP, level));
        playerController.addKeyActionFactoryMapping(LEFT, new MoveFactory(Direction.LEFT, level));
        playerController.addKeyActionFactoryMapping(A, new MoveFactory(Direction.LEFT, level));
        playerController.addKeyActionFactoryMapping(DOWN, new MoveFactory(Direction.DOWN, level));
        playerController.addKeyActionFactoryMapping(S, new MoveFactory(Direction.DOWN, level));
        playerController.addKeyActionFactoryMapping(RIGHT, new MoveFactory(Direction.RIGHT, level));
        playerController.addKeyActionFactoryMapping(D, new MoveFactory(Direction.RIGHT, level));
    }

    public static void initGame(Level level, LevelGraphics levelGraphics, ActionManager actionManager) {
        PlayerController playerController = new PlayerController();
        actionManager.addEntityActionController(playerController);
        AIController aiController = new AIController();
        actionManager.addEntityActionController(aiController);

        Initializer.initKeyBoardMappings(playerController, level);

        fillGame(level, levelGraphics, playerController, aiController);
    }

    private static void fillGame(
            Level level,
            LevelGraphics levelGraphics,
            PlayerController playerController,
            AIController aiController
    ) {
        Filler filler = new Filler(
                new FileFilling("src/main/resources/level.txt"),
//                new RandomFilling(),
                level,
                levelGraphics,
                playerController,
                aiController
        );
        filler.filling();
    }
}
