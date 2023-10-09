package ru.platformer.game.model.actions;

import ru.platformer.game.Action;

import java.util.ArrayList;

public class ActionManager {
    private final ArrayList<EntityController> entityActionControllers = new ArrayList<>();


    public void addEntityActionController(EntityController entityActionController){
        entityActionControllers.add(entityActionController);
    }

    public ArrayList<Action> generateGameEntitiesActions(){
        ArrayList<Action> actions = new ArrayList<>();
        entityActionControllers.forEach(entityActionController -> actions.addAll(entityActionController.generateActions()));

        return actions;
    }

    public void applyActions(ArrayList<Action> actions){
        actions.forEach(Action::apply);
    }
}