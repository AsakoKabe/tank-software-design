package ru.mipt.bit.platformer.action;

import com.badlogic.gdx.math.GridPoint2;

public class MoveFactory implements ActionFactory{
    private final Direction direction;

    public MoveFactory(Direction direction) {
        this.direction = direction;
    }

    public Move create(){
        return new Move(direction.getCoordinates(), direction.getRotation());
    }
}
