package ru.platformer.game.model.entityControllers.aiControllers;

import ru.platformer.RandomEnumGenerator;
import ru.platformer.game.AIEvent;
import ru.platformer.game.Action;
import ru.platformer.game.ActionFactory;
import ru.platformer.game.GameObject;
import ru.platformer.game.model.entityControllers.AIController;
import ru.platformer.game.EntityController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RandomAIController implements EntityController, AIController {

    private final GameObject gameObject;
    private final Map<AIEvent, ActionFactory> aiEventToActionFactory = new HashMap<>();

    public RandomAIController(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    @Override
    public ArrayList<Action> generateActions() {
        ArrayList<Action> actions = new ArrayList<>();

        Action action = getAction();
        if (action != null){
            actions.add(action);
        }

        return actions;
    }


    public Action getAction() {
        RandomEnumGenerator<AIEvent> reg = new RandomEnumGenerator<>(AIEvent.class);
        AIEvent aiEvent = reg.randomEnum();
        return aiEventToActionFactory.get(aiEvent).create(gameObject);
    }

    @Override
    public void addActionFactoryByAIEvent(AIEvent aiEvent, ActionFactory actionFactory){
        aiEventToActionFactory.put(aiEvent, actionFactory);
    }
}
