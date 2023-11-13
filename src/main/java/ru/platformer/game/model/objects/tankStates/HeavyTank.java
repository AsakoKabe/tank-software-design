package ru.platformer.game.model.objects.tankStates;

import com.badlogic.gdx.math.GridPoint2;
import ru.platformer.game.Direction;
import ru.platformer.game.model.CollisionDetector;
import ru.platformer.game.model.objects.Bullet;
import ru.platformer.game.model.objects.Level;
import ru.platformer.game.model.objects.Tank;
import ru.platformer.game.model.objects.TankState;

public class HeavyTank implements TankState {
    private final Tank tank;

    public HeavyTank(Tank tank) {
        this.tank = tank;
    }

    @Override
    public void continueMovement(float deltaTime) {
        tank.computeMovementProgress(deltaTime, tank.getSpeed() * 3);
        if (tank.isNotMoving()) {
            tank.moveToDestinationCoordinates();
        }
    }

    @Override
    public void shoot() {
//        dont shoot
    }
}
