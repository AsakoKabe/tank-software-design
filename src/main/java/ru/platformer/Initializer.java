package ru.platformer;

import ru.platformer.game.AIEvent;
import ru.platformer.game.Direction;
import ru.platformer.game.model.CollisionDetector;
import ru.platformer.game.model.actions.move.MoveFactory;
import ru.platformer.game.model.actions.shoot.ShootFactory;
import ru.platformer.game.model.entityControllers.AIController;
import ru.platformer.game.model.entityControllers.KeyboardController;
import ru.platformer.game.model.objects.Level;

import static com.badlogic.gdx.Input.Keys.*;

public class Initializer {
    public static void initKeyBoardMappings(
            KeyboardController keyboardController,
            CollisionDetector collisionDetector,
            Level level
    ) {
        keyboardController.addKeyActionFactoryMapping(
                UP,
                new MoveFactory(Direction.UP, collisionDetector, level.getWidth(),
                        level.getHeight()
                )
        );
        keyboardController.addKeyActionFactoryMapping(
                W,
                new MoveFactory(Direction.UP, collisionDetector, level.getWidth(),
                        level.getHeight()
                )
        );
        keyboardController.addKeyActionFactoryMapping(
                LEFT,
                new MoveFactory(Direction.LEFT, collisionDetector, level.getWidth(),
                        level.getHeight()
                )
        );
        keyboardController.addKeyActionFactoryMapping(
                A,
                new MoveFactory(Direction.LEFT, collisionDetector, level.getWidth(),
                        level.getHeight()
                )
        );
        keyboardController.addKeyActionFactoryMapping(
                DOWN,
                new MoveFactory(Direction.DOWN, collisionDetector, level.getWidth(),
                        level.getHeight()
                )
        );
        keyboardController.addKeyActionFactoryMapping(
                S,
                new MoveFactory(Direction.DOWN, collisionDetector, level.getWidth(),
                        level.getHeight()
                )
        );
        keyboardController.addKeyActionFactoryMapping(
                RIGHT,
                new MoveFactory(Direction.RIGHT, collisionDetector, level.getWidth(),
                        level.getHeight()
                )
        );
        keyboardController.addKeyActionFactoryMapping(
                D,
                new MoveFactory(Direction.RIGHT, collisionDetector, level.getWidth(),
                        level.getHeight()
                )
        );
        keyboardController.addKeyActionFactoryMapping(
                SPACE,
                new ShootFactory(level)
        );
    }

    public static void initAIEventMappings(
            AIController aiController, CollisionDetector collisionDetector,
            Level level
    ) {
        aiController.addActionFactoryByAIEvent(AIEvent.MoveUp, new MoveFactory(Direction.UP,
                collisionDetector, level.getWidth(),
                level.getHeight()
        ));
        aiController.addActionFactoryByAIEvent(AIEvent.MoveDown, new MoveFactory(Direction.DOWN,
                collisionDetector, level.getWidth(),
                level.getHeight()
        ));
        aiController.addActionFactoryByAIEvent(AIEvent.MoveRight, new MoveFactory(Direction.RIGHT
                , collisionDetector, level.getWidth(),
                level.getHeight()
        ));
        aiController.addActionFactoryByAIEvent(AIEvent.MoveLeft, new MoveFactory(Direction.LEFT,
                collisionDetector, level.getWidth(),
                level.getHeight()
        ));
        aiController.addActionFactoryByAIEvent(AIEvent.Shoot, new ShootFactory(level));
    }
}
