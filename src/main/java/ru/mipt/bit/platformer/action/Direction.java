package ru.mipt.bit.platformer.action;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.gameEntities.GameEntity;

import static ru.mipt.bit.platformer.util.GdxGameUtils.collides;

public enum Direction implements Action{
    UP(new GridPoint2(0, 1), 90f),
    DOWN(new GridPoint2(0, -1), -90f),
    LEFT(new GridPoint2(-1, 0), 180f),
    RIGHT(new GridPoint2(1, 0), 0f),
    ;

    Direction(GridPoint2 coordinates, float rotation) {
        this.coordinates = coordinates;
        this.rotation = rotation;
    }

    public void apply(GameEntity gameEntity) {
        gameEntity.moveTo(coordinates, rotation);
//        if (!tank.isMoving() && newDirection != null) {
//            GridPoint2 destinationCoordinates = newDirection.apply(tank.getCurrentCoordinates());
//            if (!collides(destinationCoordinates, obstacle.getCurrentCoordinates())){
//                tank.moveTo(destinationCoordinates);
//            }
//            tank.setRotation(newDirection.getRotation());
//        }
    }

    private final float rotation;
    private final GridPoint2 coordinates;

    public float getRotation() {
        return rotation;
    }

    public GridPoint2 getCoordinates() {
        return coordinates;
    }


    public GridPoint2 computeCoordinates(GridPoint2 movableCoordinates) {
        return movableCoordinates.cpy().add(coordinates);
    }
}
