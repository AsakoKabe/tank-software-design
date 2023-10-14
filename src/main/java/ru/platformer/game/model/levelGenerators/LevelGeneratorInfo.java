package ru.platformer.game.model.levelGenerators;

import ru.platformer.game.GameObject;
import ru.platformer.game.graphics.LevelGraphics;
import ru.platformer.game.model.Level;

public class LevelGeneratorInfo {
    private final GameObject playerGameObject;
    private final LevelGraphics levelGraphics;
    private final Level level;

    public LevelGeneratorInfo(Level level, LevelGraphics levelGraphics, GameObject playerGameObject) {
        this.level = level;
        this.levelGraphics = levelGraphics;
        this.playerGameObject = playerGameObject;
    }

    public GameObject getPlayerGameObject() {
        return playerGameObject;
    }

    public LevelGraphics getLevelGraphics() {
        return levelGraphics;
    }

    public Level getLevel() {
        return level;
    }
}
