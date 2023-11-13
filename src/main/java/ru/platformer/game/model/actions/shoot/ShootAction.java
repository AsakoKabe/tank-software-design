package ru.platformer.game.model.actions.shoot;

import ru.platformer.game.Action;
import ru.platformer.game.model.objects.Bullet;
import ru.platformer.game.model.objects.Level;
import ru.platformer.game.model.Shooter;

public class ShootAction implements Action {
    private final Shooter shooter;

    public ShootAction(Shooter shooter, Level level) {
        this.shooter = shooter;
    }

    @Override
    public void apply() {
        shooter.createBullet();
    }

}
