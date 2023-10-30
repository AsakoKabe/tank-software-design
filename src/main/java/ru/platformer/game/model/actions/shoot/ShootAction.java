package ru.platformer.game.model.actions.shoot;

import ru.platformer.game.Action;
import ru.platformer.game.model.CollisionDetector;
import ru.platformer.game.model.entityControllers.BulletController;
import ru.platformer.game.model.objects.Bullet;
import ru.platformer.game.model.objects.Level;
import ru.platformer.game.model.Shooter;

public class ShootAction implements Action {
    private final BulletController bulletController;
    private final Shooter shooter;
    private final Level level;

    public ShootAction(Shooter shooter, Level level,
                       BulletController bulletController
    ) {
        this.shooter = shooter;
        this.level = level;
        this.bulletController = bulletController;
    }

    @Override
    public void apply() {
        Bullet bullet = shooter.createBullet();
        if (created(bullet)){
            if (isAddBullet(bullet)){
                level.addGameObject(bullet);
            }
        }
    }

    private boolean isAddBullet(Bullet bullet) {
        return bulletController.addBullet(bullet);
    }

    private static boolean created(Bullet bullet) {
        return bullet != null;
    }
}
