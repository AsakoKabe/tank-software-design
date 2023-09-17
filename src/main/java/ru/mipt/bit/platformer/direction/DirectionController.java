package ru.mipt.bit.platformer.direction;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.GridPoint2;

import static com.badlogic.gdx.Input.Keys.*;
import static com.badlogic.gdx.Input.Keys.D;
import static com.badlogic.gdx.math.MathUtils.isEqual;

public class DirectionController implements CalculateDirection {

    @Override
    public Directions getNewDirection(Input input) {
        Directions newDirection = Directions.STAY;

        if (input.isKeyPressed(UP) || input.isKeyPressed(W)) {
            newDirection = Directions.UP;
        }
        if (Gdx.input.isKeyPressed(LEFT) || Gdx.input.isKeyPressed(A)) {
            newDirection = Directions.LEFT;
        }
        if (Gdx.input.isKeyPressed(DOWN) || Gdx.input.isKeyPressed(S)) {
            newDirection = Directions.DOWN;
        }
        if (Gdx.input.isKeyPressed(RIGHT) || Gdx.input.isKeyPressed(D)) {
            newDirection = Directions.RIGHT;
        }

        return newDirection;
    }
}
