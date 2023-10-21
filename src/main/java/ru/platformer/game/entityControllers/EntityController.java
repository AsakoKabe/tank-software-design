package ru.platformer.game.entityControllers;

import ru.platformer.game.Action;
import ru.platformer.game.GameObject;

import java.util.ArrayList;

public interface EntityController {
    ArrayList<Action> generateActions();
}
