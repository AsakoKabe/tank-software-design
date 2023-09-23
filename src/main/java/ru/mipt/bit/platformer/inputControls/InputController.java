package ru.mipt.bit.platformer.inputControls;

import com.badlogic.gdx.Gdx;

import static com.badlogic.gdx.Input.Keys.*;


import java.util.HashMap;
import java.util.Map;

public class InputController {
    private final Map<Integer, Direction> keyToDirectionMap = new HashMap<>();

    public Direction getDirection() {
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