package ru.mipt.bit.platformer.action;

import ru.mipt.bit.platformer.gameEntities.GameEntity;

import java.util.ArrayList;
import java.util.HashMap;

public class ActionController {
    private InputController inputController;

    public ActionController(InputController inputController) {
        this.inputController = inputController;
//        AIController
    }

    public HashMap<GameEntity, Action> generateGameEntitiesActions(){
        HashMap<GameEntity, Action> gameEntitiesActions = new HashMap<>();
        gameEntitiesActions.putAll(inputController.getActionsGameEntities());
//        put AI controller
        return gameEntitiesActions;
    }

    public void applyActions(HashMap<GameEntity, Action> gameEntityAction){
        gameEntityAction.forEach((gameEntity, action) -> {
            action.apply(gameEntity);
        });
    }
}
