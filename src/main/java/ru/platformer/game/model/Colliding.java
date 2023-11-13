package ru.platformer.game.model;

import com.badlogic.gdx.math.GridPoint2;

public interface Colliding {
    boolean isBusyCoordinate(GridPoint2 coordinates);
}
