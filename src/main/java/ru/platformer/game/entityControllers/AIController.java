package ru.platformer.game.entityControllers;

import ru.platformer.game.AIEvent;
import ru.platformer.game.ActionFactory;

public interface AIController {
    void addAIEventFactoryMapping(AIEvent aiEvent, ActionFactory actionFactory);
}
