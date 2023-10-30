package ru.platformer;

import ru.platformer.game.AIEvent;
import ru.platformer.game.Direction;
import ru.platformer.game.model.CollisionDetector;
import ru.platformer.game.model.actions.move.MoveFactory;
import ru.platformer.game.model.actions.shoot.ShootFactory;
import ru.platformer.game.model.entityControllers.AIController;
import ru.platformer.game.model.entityControllers.BulletController;
import ru.platformer.game.model.entityControllers.PlayerController;
import ru.platformer.game.model.objects.Level;

import static com.badlogic.gdx.Input.Keys.*;

public class Initializer {
    public static void initKeyBoardMappings(
            PlayerController playerController,
            CollisionDetector collisionDetector,
            Level level,
            BulletController bulletController
    ) {
        playerController.addKeyActionFactoryMapping(
                UP,
                new MoveFactory(Direction.UP, collisionDetector, level.getWidth(),
                        level.getHeight()
                )
        );
        playerController.addKeyActionFactoryMapping(
                W,
                new MoveFactory(Direction.UP, collisionDetector, level.getWidth(),
                        level.getHeight()
                )
        );
        playerController.addKeyActionFactoryMapping(
                LEFT,
                new MoveFactory(Direction.LEFT, collisionDetector, level.getWidth(),
                        level.getHeight()
                )
        );
        playerController.addKeyActionFactoryMapping(
                A,
                new MoveFactory(Direction.LEFT, collisionDetector, level.getWidth(),
                        level.getHeight()
                )
        );
        playerController.addKeyActionFactoryMapping(
                DOWN,
                new MoveFactory(Direction.DOWN, collisionDetector, level.getWidth(),
                        level.getHeight()
                )
        );
        playerController.addKeyActionFactoryMapping(
                S,
                new MoveFactory(Direction.DOWN, collisionDetector, level.getWidth(),
                        level.getHeight()
                )
        );
        playerController.addKeyActionFactoryMapping(
                RIGHT,
                new MoveFactory(Direction.RIGHT, collisionDetector, level.getWidth(),
                        level.getHeight()
                )
        );
        playerController.addKeyActionFactoryMapping(
                D,
                new MoveFactory(Direction.RIGHT, collisionDetector, level.getWidth(),
                        level.getHeight()
                )
        );
        playerController.addKeyActionFactoryMapping(
                SPACE,
                new ShootFactory(level, bulletController)
        );
    }

    public static void initAIEventMappings(
            AIController aiController, CollisionDetector collisionDetector,
            Level level, BulletController bulletController
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
        aiController.addActionFactoryByAIEvent(AIEvent.Shoot, new ShootFactory(level, bulletController));
    }
}
