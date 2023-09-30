package ru.mipt.bit.platformer.action;

import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.gameEntities.GameEntity;

import static com.badlogic.gdx.Input.Keys.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InputController {
    private final Map<Integer, Action> keyToDirectionMap = new HashMap<>();
    private ArrayList<GameEntity> gameEntities;

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

    public Action getAction() {
        for (Integer key : keyToDirectionMap.keySet()) {
            if (Gdx.input.isKeyPressed(key)) {
                return keyToDirectionMap.get(key);
            }
        }
        return null;
    }

    public void initKeyBoardMappings() {
        keyToDirectionMap.put(UP, Move.UP);
        keyToDirectionMap.put(W, Move.UP);
        keyToDirectionMap.put(LEFT, Move.LEFT);
        keyToDirectionMap.put(A, Move.LEFT);
        keyToDirectionMap.put(DOWN, Move.DOWN);
        keyToDirectionMap.put(S, Move.DOWN);
        keyToDirectionMap.put(RIGHT, Move.RIGHT);
        keyToDirectionMap.put(D, Move.RIGHT);
    }

}