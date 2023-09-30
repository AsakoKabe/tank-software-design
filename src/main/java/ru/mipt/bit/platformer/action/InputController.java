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

    public HashMap<GameEntity, Action> getActionsGameEntities(){
        HashMap<GameEntity, Action> gameEntityAction = new HashMap<>();
        for (GameEntity gameEntity: gameEntities){
            gameEntityAction.put(gameEntity, getAction());
        }

        return gameEntityAction;
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
        keyToDirectionMap.put(UP, Direction.UP);
        keyToDirectionMap.put(W, Direction.UP);
        keyToDirectionMap.put(LEFT, Direction.LEFT);
        keyToDirectionMap.put(A, Direction.LEFT);
        keyToDirectionMap.put(DOWN, Direction.DOWN);
        keyToDirectionMap.put(S, Direction.DOWN);
        keyToDirectionMap.put(RIGHT, Direction.RIGHT);
        keyToDirectionMap.put(D, Direction.RIGHT);
    }
}