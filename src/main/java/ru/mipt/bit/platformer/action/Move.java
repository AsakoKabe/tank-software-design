package ru.mipt.bit.platformer.action;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.gameEntities.GameEntity;

class Move implements Action{

    Move(Direction direction) {
        this.coordinates = direction.getCoordinates();
        this.rotation = direction.getRotation();
    }

    public GridPoint2 getCoordinates() {
        return coordinates;
    }

    public void apply(GameEntity gameEntity) {
        gameEntity.moveToDirection(coordinates, rotation);
    }

    public void resetCoordinates(){
        coordinates.set(new GridPoint2(0, 0));
    }

    private final float rotation;
    private final GridPoint2 coordinates;

}
