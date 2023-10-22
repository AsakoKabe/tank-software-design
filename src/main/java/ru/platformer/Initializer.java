package ru.platformer;

import ru.platformer.game.AIEvent;
import ru.platformer.game.Direction;
import ru.platformer.game.entityControllers.AIController;
import ru.platformer.game.model.*;
import ru.platformer.game.entityControllers.PlayerController;
import ru.platformer.game.model.actions.move.MoveFactory;

import static com.badlogic.gdx.Input.Keys.*;
import static com.badlogic.gdx.Input.Keys.D;

public class Initializer {
    public static void initKeyBoardMappings(PlayerController playerController, CollisionDetector collisionDetector) {
        playerController.addKeyActionFactoryMapping(UP, new MoveFactory(Direction.UP, collisionDetector));
        playerController.addKeyActionFactoryMapping(W, new MoveFactory(Direction.UP, collisionDetector));
        playerController.addKeyActionFactoryMapping(LEFT, new MoveFactory(Direction.LEFT, collisionDetector));
        playerController.addKeyActionFactoryMapping(A, new MoveFactory(Direction.LEFT, collisionDetector));
        playerController.addKeyActionFactoryMapping(DOWN, new MoveFactory(Direction.DOWN, collisionDetector));
        playerController.addKeyActionFactoryMapping(S, new MoveFactory(Direction.DOWN, collisionDetector));
        playerController.addKeyActionFactoryMapping(RIGHT, new MoveFactory(Direction.RIGHT, collisionDetector));
        playerController.addKeyActionFactoryMapping(D, new MoveFactory(Direction.RIGHT, collisionDetector));
    }

    public static void initAIEventMappings(AIController aiController, CollisionDetector collisionDetector) {
        aiController.addActionFactoryByAIEvent(AIEvent.MoveUp, new MoveFactory(Direction.UP, collisionDetector));
        aiController.addActionFactoryByAIEvent(AIEvent.MoveDown, new MoveFactory(Direction.DOWN, collisionDetector));
        aiController.addActionFactoryByAIEvent(AIEvent.MoveRight, new MoveFactory(Direction.RIGHT, collisionDetector));
        aiController.addActionFactoryByAIEvent(AIEvent.MoveLeft, new MoveFactory(Direction.LEFT, collisionDetector));
    }
}
