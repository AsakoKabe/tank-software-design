package ru.mipt.bit.platformer.action;

import com.badlogic.gdx.math.GridPoint2;

public enum Direction {
    UP(new GridPoint2(0, 1), 90f),
    DOWN(new GridPoint2(0, -1), -90f),
    LEFT(new GridPoint2(-1, 0), 180f),
    RIGHT(new GridPoint2(1, 0), 0f),
    ;
//    ;

    private final GridPoint2 coordinates;
    private final float rotation;

    Direction(GridPoint2 coordinates, float rotation) {
        this.coordinates = coordinates;
        this.rotation = rotation;
    }

    public GridPoint2 getCoordinates() {
        return coordinates;
    }

    public float getRotation() {
        return rotation;
    }
}
