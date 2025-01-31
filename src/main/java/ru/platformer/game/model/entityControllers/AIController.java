package ru.platformer.game.model.entityControllers;

import ru.platformer.game.AIEvent;
import ru.platformer.game.ActionFactory;

public interface AIController {
    void addActionFactoryByAIEvent(AIEvent aiEvent, ActionFactory actionFactory);
}
