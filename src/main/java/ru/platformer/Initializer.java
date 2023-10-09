package ru.platformer;

import ru.platformer.game.Direction;
import ru.platformer.game.model.*;
import ru.platformer.game.entityControllers.PlayerController;
import ru.platformer.game.model.actions.move.MoveFactory;

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


}
