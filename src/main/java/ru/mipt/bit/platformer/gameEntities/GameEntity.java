package ru.mipt.bit.platformer.gameEntities;

import com.badlogic.gdx.math.GridPoint2;

public interface GameEntity {

    public void updateState(float deltaTime);

    public void moveTo(GridPoint2 coordinates, float rotation);
}
