package ru.platformer.game.model.entityControllers;

import ru.platformer.game.AIEvent;
import ru.platformer.game.model.actions.ActionFactory;

public interface AIController {
    void addActionFactoryByAIEvent(AIEvent aiEvent, ActionFactory actionFactory);
}
