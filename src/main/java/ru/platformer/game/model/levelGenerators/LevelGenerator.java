package ru.platformer.game.model.levelGenerators;

import org.javatuples.Pair;
import ru.platformer.game.GameObject;
import ru.platformer.game.model.Level;

public interface LevelGenerator {
    Pair<Level, GameObject> generate();
}
