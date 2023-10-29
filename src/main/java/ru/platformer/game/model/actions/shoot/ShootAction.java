package ru.platformer.game.model.actions.shoot;

import ru.platformer.game.Action;
import ru.platformer.game.model.objects.Bullet;
import ru.platformer.game.model.objects.Level;
import ru.platformer.game.model.Shooter;

public class ShootAction implements Action {
    private Shooter shooter;
    private Level level;

    public ShootAction(Shooter shooter, Level level) {
        this.shooter = shooter;
        this.level = level;
    }

    @Override
    public void apply() {
        Bullet bullet = shooter.createBullet();
        level.addGameObject(bullet);
    }
}
