package ru.platformer.game.entityControllers;

import com.badlogic.gdx.Gdx;
import ru.platformer.game.Action;
import ru.platformer.game.GameObject;
import ru.platformer.game.ActionFactory;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PlayerController implements EntityController {
    private final Map<Integer, ActionFactory> keyToActionFactory = new HashMap<>();
    private GameObject player;

    public void addGameObject(GameObject player){
        this.player = player;
    }

    public ArrayList<Action> generateActions(){
        ArrayList<Action> actions = new ArrayList<>();

        Action action = getAction();
        if (action != null){
            actions.add(action);
        }

        return actions;
    }

    public void addKeyActionFactoryMapping(Integer key, ActionFactory actionFactory){
        keyToActionFactory.put(key, actionFactory);

    }

    private Action getAction() {
        for (Integer key : keyToActionFactory.keySet()) {
            if (Gdx.input.isKeyPressed(key)) {
                return keyToActionFactory.get(key).create(player);
            }
        }
        return null;
    }

}