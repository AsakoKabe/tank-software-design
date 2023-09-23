package ru.mipt.bit.platformer.inputControls;

import com.badlogic.gdx.math.GridPoint2;

public enum Direction {
    UP(new GridPoint2(0, 1), 90f),
    DOWN(new GridPoint2(0, -1), -90f),
    LEFT(new GridPoint2(-1, 0), 180f),
    RIGHT(new GridPoint2(1, 0), 0f),
    ;

    Direction(GridPoint2 coordinates, float rotation) {
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

    public GridPoint2 apply(GridPoint2 point) {
        return point.cpy().add(coordinates);
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
