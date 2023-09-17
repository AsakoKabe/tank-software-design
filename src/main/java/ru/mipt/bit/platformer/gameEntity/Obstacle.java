package ru.mipt.bit.platformer.gameEntity;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.map.Map;

import static ru.mipt.bit.platformer.util.GdxGameUtils.moveRectangleAtTileCenter;

public class Obstacle extends GameEntity {
    public Obstacle(
            String filePathTexture,
            GridPoint2 startCoordinates,
            Map map
    ) {
        super(filePathTexture, startCoordinates);
        moveRectangleAtTileCenter(
                map.getGroundLayer(),
                rectangle,
                destinationCoordinates
        );
    }
}
