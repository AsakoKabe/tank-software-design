package ru.mipt.bit.platformer.gameEntity;

import com.badlogic.gdx.math.GridPoint2;

public class Player extends GameEntity {
    float movementProgress;

    public Player(
            String filePathTexture,
            GridPoint2 startCoordinates,
            float rotation,
            float movementProgress
        ) {
        super(filePathTexture, startCoordinates);
        this.rotation = rotation;
        this.movementProgress = movementProgress;

    }

    public Player(
            String filePathTexture,
            GridPoint2 startCoordinates
    ) {
        super(filePathTexture, startCoordinates);
        this.rotation = 0f;
        this.movementProgress = 1f;
    }

    public void setMovementProgress(float movementProgress) {
        this.movementProgress = movementProgress;
    }

    public float getMovementProgress() {
        return movementProgress;
    }


}
