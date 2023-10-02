package ru.mipt.bit.platformer.action;

import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.gameEntities.GameEntity;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InputController {
    private final Map<Integer, ActionFactory> keyToActionFactory = new HashMap<>();
    private final ArrayList<GameEntity> gameEntities;

    public InputController() {
        this.gameEntities = new ArrayList<>();
    }

    public void addGameEntity(GameEntity gameEntity){
        gameEntities.add(gameEntity);
    }

    public HashMap<GameEntity, Action> getGameEntitiesActions(){
        HashMap<GameEntity, Action> gameEntitiesActions = new HashMap<>();
        for (GameEntity gameEntity: gameEntities){
            gameEntitiesActions.put(gameEntity, getAction());
        }

        return gameEntitiesActions;
    }

    public void addKeyActionFactoryMapping(Integer key, ActionFactory actionFactory){
        keyToActionFactory.put(key, actionFactory);

    }

    public Action getAction() {
        for (Integer key : keyToActionFactory.keySet()) {
            if (Gdx.input.isKeyPressed(key)) {
                return keyToActionFactory.get(key).create();
            }
        }
        return null;
    }

}