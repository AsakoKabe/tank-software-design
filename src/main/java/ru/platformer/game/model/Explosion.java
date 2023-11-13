package ru.platformer.game.model;

import com.badlogic.gdx.math.GridPoint2;
import ru.platformer.game.GameObject;
import ru.platformer.game.model.objects.Level;

public class Explosion implements GameObject {
    private final GridPoint2 coordinates;
    private final Level level;
    private float ttl;

    public Explosion(GridPoint2 gridPoint2, Level level, float ttl) {
        this.coordinates = gridPoint2;
        this.level = level;
        this.ttl = ttl;
    }

    @Override
    public GridPoint2 getCurrentCoordinates() {
        return coordinates;
    }

    @Override
    public void updateState(float deltaTime) {
        ttl -= deltaTime;
        if (ttl < 0){
            level.deleteGameObject(this);
        }

    }
}
