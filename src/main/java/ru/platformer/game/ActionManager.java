package ru.platformer.game;

import java.util.ArrayList;

public class ActionManager {
    private final ArrayList<EntityController> entityActionControllers = new ArrayList<>();


    public void addEntityActionController(EntityController entityActionController) {
        entityActionControllers.add(entityActionController);
    }

    public ArrayList<Action> generateGameObjectsActions() {
        ArrayList<Action> actions = new ArrayList<>();
        entityActionControllers.forEach(entityActionController -> actions.addAll(entityActionController.generateActions()));

        return actions;
    }

    public void applyActions(ArrayList<Action> actions) {
        actions.forEach(Action::apply);
    }
}
