package ru.platformer.game;

import ru.platformer.game.Action;

import java.util.ArrayList;

public interface EntityController {
    ArrayList<Action> generateActions();
}
