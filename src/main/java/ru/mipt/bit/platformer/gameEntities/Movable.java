package ru.mipt.bit.platformer.gameEntities;

import ru.mipt.bit.platformer.action.Direction;

public interface Movable {
    void moveToDirection(Direction direction, Boolean onlyRotation);

}
