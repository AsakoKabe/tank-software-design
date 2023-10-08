package ru.mipt.bit.platformer.action;

import java.util.ArrayList;

public class ActionManager {
    private final ArrayList<EntityActionController> entityActionControllers = new ArrayList<>();


    public void addEntityActionController(EntityActionController entityActionController){
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
