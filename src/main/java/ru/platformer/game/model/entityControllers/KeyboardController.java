package ru.platformer.game.model.entityControllers;

import com.badlogic.gdx.Gdx;
import ru.platformer.game.Action;
import ru.platformer.game.EntityController;
import ru.platformer.game.model.actions.ActionFactory;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class KeyboardController<T> implements EntityController {
    private final Map<Integer, ActionFactory<T>> keyToActionFactory = new HashMap<>();
    private final T controlledObject;

    public KeyboardController(T player) {
        this.controlledObject = player;
    }

    public ArrayList<Action> generateActions(){
        ArrayList<Action> actions = new ArrayList<>();

        Action action = getAction();
        if (action != null){
            actions.add(action);
        }

        return actions;
    }

    public void addKeyActionFactoryMapping(Integer key, ActionFactory<T> actionFactory){
        keyToActionFactory.put(key, actionFactory);
    }

    private Action getAction() {
        for (Integer key : keyToActionFactory.keySet()) {
            if (Gdx.input.isKeyJustPressed(key)) {
                return keyToActionFactory.get(key).create(controlledObject);
            }
        }
        return null;
    }

}