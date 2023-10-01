package ru.mipt.bit.platformer.action;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.gameEntities.GameEntity;

public class Move implements Action{

    private boolean coordinatesReset;

    public Move(GridPoint2 coordinates, float rotation) {
        this.coordinates = coordinates;
        this.rotation = rotation;
        this.coordinatesReset = false;
    }

    public GridPoint2 getCoordinates() {
        return coordinates;
    }

    public void apply(GameEntity gameEntity) {
        if (coordinatesReset){
            gameEntity.moveTo(new GridPoint2(0, 0), rotation);
            coordinatesReset = false;
        }
        else {
            gameEntity.moveTo(coordinates, rotation);
        }
    }

    public void resetCoordinates(){
        coordinatesReset = true;
    }

    private final float rotation;

    public float getRotation() {
        return rotation;
    }

    private final GridPoint2 coordinates;

}
