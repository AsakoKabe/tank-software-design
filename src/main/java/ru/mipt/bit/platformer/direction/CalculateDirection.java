package ru.mipt.bit.platformer.direction;

import com.badlogic.gdx.Input;

public interface CalculateDirection {
    public Directions getNewDirection(Input input);
}
