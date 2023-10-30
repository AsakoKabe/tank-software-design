package ru.platformer.game.model.actions.shoot;

import ru.platformer.game.Action;
import ru.platformer.game.model.entityControllers.BulletController;
import ru.platformer.game.model.objects.Bullet;
import ru.platformer.game.model.objects.Level;
import ru.platformer.game.model.Shooter;

public class ShootAction implements Action {
    private final BulletController bulletController;
    private Shooter shooter;
    private Level level;

    public ShootAction(Shooter shooter, Level level,
                       BulletController bulletController) {
        this.shooter = shooter;
        this.level = level;
        this.bulletController = bulletController;
    }

    @Override
    public void apply() {
        Bullet bullet = shooter.createBullet();
        if (created(bullet)){
            level.addGameObject(bullet);
            bulletController.addBullet(bullet);
        }
    }

    private static boolean created(Bullet bullet) {
        return bullet != null;
    }
}
