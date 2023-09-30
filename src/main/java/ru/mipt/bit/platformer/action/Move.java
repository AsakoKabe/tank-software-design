package ru.mipt.bit.platformer.action;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.gameEntities.GameEntity;

public enum Move implements Action{
    UP(new GridPoint2(0, 1), 90f),
    DOWN(new GridPoint2(0, -1), -90f),
    LEFT(new GridPoint2(-1, 0), 180f),
    RIGHT(new GridPoint2(1, 0), 0f),
    ;

    private boolean coordinatesReseted;

    Move(GridPoint2 coordinates, float rotation) {
        this.coordinates = coordinates;
        this.rotation = rotation;
        this.coordinatesReseted = false;
    }

    public GridPoint2 getCoordinates() {
        return coordinates;
    }

    public void apply(GameEntity gameEntity) {
        if (coordinatesReseted){
            gameEntity.moveToDirection(new GridPoint2(0, 0), rotation);
            coordinatesReseted = false;
        }
        else {
            gameEntity.moveToDirection(coordinates, rotation);
        }
    }

    public void resetCoordinates(){
        coordinatesReseted = true;
    }

    private final float rotation;
    private final GridPoint2 coordinates;

}
