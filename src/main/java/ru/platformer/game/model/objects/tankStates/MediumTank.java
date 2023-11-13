package ru.platformer.game.model.objects.tankStates;

import com.badlogic.gdx.math.GridPoint2;
import ru.platformer.game.Direction;
import ru.platformer.game.model.CollisionDetector;
import ru.platformer.game.model.objects.Bullet;
import ru.platformer.game.model.objects.Level;
import ru.platformer.game.model.objects.Tank;
import ru.platformer.game.model.objects.TankState;

public class MediumTank implements TankState {
    private final Tank tank;
    private final Level level;
    private final CollisionDetector collisionDetector;

    public MediumTank(Level level, Tank tank, CollisionDetector collisionDetector) {
        this.tank = tank;
        this.level = level;
        this.collisionDetector = collisionDetector;
    }

    @Override
    public void continueMovement(float deltaTime) {
        tank.computeMovementProgress(deltaTime, tank.getSpeed() * 2);
        if (tank.isNotMoving()) {
            tank.moveToDestinationCoordinates();
        }
    }
    private GridPoint2 createBulletCoordinates() {
        return tank.getCurrentCoordinates().cpy().add(tank.getDirection().getCoordinates());
    }
    @Override
    public void shoot() {
        if (tank.isNotMoving()) {
            Bullet bullet = new Bullet(
                    createBulletCoordinates(),
                    tank.getDirection(),
                    tank.getDamage(),
                    0.25f,
                    level,
                    collisionDetector
            );
            level.addGameObject(bullet);
        }
    }
}
