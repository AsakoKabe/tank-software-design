package ru.platformer.game;

import com.badlogic.gdx.math.GridPoint2;

public enum Direction {
    UP(new GridPoint2(0, 1), 90f),
    DOWN(new GridPoint2(0, -1), -90f),
    LEFT(new GridPoint2(-1, 0), 180f),
    RIGHT(new GridPoint2(1, 0), 0f),
    ;

    private final GridPoint2 coordinates;
    private final float rotation;

    Direction(GridPoint2 coordinates, float rotation) {
        this.coordinates = coordinates;
        this.rotation = rotation;
    }

    public float getRotation() {
        return rotation;
    }

    public GridPoint2 applyCoordinates(GridPoint2 coordinates){
        return coordinates.cpy().add(this.coordinates);
    }

    public GridPoint2 getCoordinates() {
        return coordinates;
    }
}
