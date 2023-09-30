package ru.mipt.bit.platformer.action;

import ru.mipt.bit.platformer.gameEntities.GameEntity;

import java.util.HashMap;

public class ActionController {
    private InputController inputController;

    public ActionController(InputController inputController) {
        this.inputController = inputController;
        //  AIController
    }

    public HashMap<GameEntity, Action> generateGameEntitiesActions(){
        HashMap<GameEntity, Action> gameEntitiesActions = new HashMap<>();
        gameEntitiesActions.putAll(inputController.getGameEntitiesActions());
        //   put AI actions

        return gameEntitiesActions;
    }

    public void applyActions(HashMap<GameEntity, Action> gameEntityAction){
        gameEntityAction.forEach((gameEntity, action) -> {
            if (action != null) {
                action.apply(gameEntity);
            }
        });
    }
}
