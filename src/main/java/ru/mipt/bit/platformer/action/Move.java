package ru.mipt.bit.platformer.action;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.gameEntities.GameEntity;

class Move implements Action{

    private boolean coordinatesReset;

    Move(GridPoint2 coordinates, float rotation) {
        this.coordinates = coordinates;
        this.rotation = rotation;
        this.coordinatesReset = false;
    }

    public GridPoint2 getCoordinates() {
        return coordinates;
    }

    public void apply(GameEntity gameEntity) {
        if (coordinatesReset){
            gameEntity.moveToDirection(new GridPoint2(0, 0), rotation);
            coordinatesReset = false;
        }
        else {
            gameEntity.moveToDirection(coordinates, rotation);
        }
    }

    public void resetCoordinates(){
        coordinatesReset = true;
    }

    private final float rotation;
    private final GridPoint2 coordinates;

}
