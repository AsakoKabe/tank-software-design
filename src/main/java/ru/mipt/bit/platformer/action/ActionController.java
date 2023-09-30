package ru.mipt.bit.platformer.action;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.gameEntities.GameEntity;
import ru.mipt.bit.platformer.gameEntities.Level;

import java.util.HashMap;

public class ActionController {
    private final Level level;
    private InputController inputController;

    public ActionController(Level level, InputController inputController) {
        this.inputController = inputController;
        this.level = level;
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
            if (validateAction(gameEntity, action)) {
                action.apply(gameEntity);
            }
        });
    }

    private boolean validateAction(GameEntity gameEntity, Action action) {
        if (action == null){
            return false;
        }

        // здесь будут проверки и для других действий, но пока не придумал как это обрабатывать
        return validateMoveAction(gameEntity, (Move) action);
    }

    private boolean validateMoveAction(GameEntity gameEntity, Move move) {
        GridPoint2 coordinates = gameEntity.getCurrentCoordinates().cpy().add(move.getCoordinates());
        if (level.collidesExist(coordinates)){
            move.resetCoordinates();
        }
        return true;
    }
}
