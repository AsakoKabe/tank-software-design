package ru.mipt.bit.platformer.action;

import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.gameEntities.GameEntity;

import static com.badlogic.gdx.Input.Keys.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InputController {
    private final Map<Integer, ActionFactory> keyToDirectionMap = new HashMap<>();
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
                return keyToDirectionMap.get(key).create();
            }
        }
        return null;
    }

    public void initKeyBoardMappings() {

        keyToDirectionMap.put(UP, new MoveFactory(Direction.UP));
        keyToDirectionMap.put(W, new MoveFactory(Direction.UP));
        keyToDirectionMap.put(LEFT, new MoveFactory(Direction.LEFT));
        keyToDirectionMap.put(A, new MoveFactory(Direction.LEFT));
        keyToDirectionMap.put(DOWN, new MoveFactory(Direction.DOWN));
        keyToDirectionMap.put(S, new MoveFactory(Direction.DOWN));
        keyToDirectionMap.put(RIGHT, new MoveFactory(Direction.RIGHT));
        keyToDirectionMap.put(D, new MoveFactory(Direction.RIGHT));
    }

}