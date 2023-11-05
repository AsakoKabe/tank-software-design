package ru.platformer.game.graphics;

import com.badlogic.gdx.Gdx;
import ru.platformer.game.Action;
import ru.platformer.game.graphics.actions.GraphicsActionFactory;
import ru.platformer.game.EntityController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LevelGraphicsController implements EntityController {
    private final LevelGraphics levelGraphics;
    private final Map<Integer, GraphicsActionFactory> keyToActionFactory = new HashMap<>();


    public LevelGraphicsController(LevelGraphics levelGraphics) {
        this.levelGraphics = levelGraphics;
    }

    public ArrayList<Action> generateActions(){
        ArrayList<Action> actions = new ArrayList<>();

        Action action = getAction();
        if (action != null){
            actions.add(action);
        }

        return actions;
    }

    public void addKeyActionFactoryMapping(Integer key, GraphicsActionFactory actionFactory){
        keyToActionFactory.put(key, actionFactory);
    }

    private Action getAction() {
        for (Integer key : keyToActionFactory.keySet()) {
            if (Gdx.input.isKeyJustPressed(key)) {
                return keyToActionFactory.get(key).create(levelGraphics);
            }
        }
        return null;
    }
}
