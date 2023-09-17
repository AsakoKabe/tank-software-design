package ru.mipt.bit.platformer.direction;

import com.badlogic.gdx.math.GridPoint2;

public enum Directions {
    UP(new GridPoint2(0, 1), 90f),
    DOWN(new GridPoint2(0, -1), -90f),
    LEFT(new GridPoint2(-1, 0), 180f),
    RIGHT(new GridPoint2(1, 0), 0f),
    STAY(new GridPoint2(0, 0), 0f)
    ;

    Directions(GridPoint2 coordinates, float rotation) {
        this.coordinates = coordinates;
        this.rotation = rotation;
    }

    @Override
    public String toString() {
        return "Directions{" +
                "rotation=" + rotation +
                ", coordinates=" + coordinates +
                '}';
    }

    private final float rotation;
    private final GridPoint2 coordinates;

    public float getRotation() {
        return rotation;
    }

    public GridPoint2 getCoordinates() {
        return coordinates;
    }


}
