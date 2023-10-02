package ru.mipt.bit.platformer.action;

import ru.mipt.bit.platformer.gameEntities.GameEntity;
import ru.mipt.bit.platformer.gameEntities.Level;

import java.util.HashMap;

public class ActionController {
    private final InputController inputController;

    public ActionController(InputController inputController) {
        this.inputController = inputController;
        //  AIController
    }

    public HashMap<GameEntity, Action> generateGameEntitiesActions(){
        HashMap<GameEntity, Action> gameEntitiesActions = new HashMap<>();

        gameEntitiesActions.putAll(inputController.generateAction());
        //   put AI actions

        return gameEntitiesActions;
    }

    public void applyActions(HashMap<GameEntity, Action> gameEntityAction){
        gameEntityAction.forEach((gameEntity, action) -> {
            if (action.validate(gameEntity)) {
                action.apply(gameEntity);
            }
        });
    }


}
