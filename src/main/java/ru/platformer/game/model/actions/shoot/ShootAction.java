package ru.platformer.game.model.actions.shoot;

import com.badlogic.gdx.Gdx;
import ru.platformer.game.Action;
import ru.platformer.game.model.entityControllers.IndependentMovableObjectsController;
import ru.platformer.game.model.objects.Bullet;
import ru.platformer.game.model.objects.Level;
import ru.platformer.game.model.Shooter;

public class ShootAction implements Action {
    private final IndependentMovableObjectsController independentMovableObjectsController;
    private Shooter shooter;
    private Level level;

    public ShootAction(Shooter shooter, Level level,
                       IndependentMovableObjectsController independentMovableObjectsController) {
        this.shooter = shooter;
        this.level = level;
        this.independentMovableObjectsController = independentMovableObjectsController;
    }

    @Override
    public void apply() {
        Bullet bullet = shooter.createBullet();
        if (created(bullet)){
            level.addGameObject(bullet);
            independentMovableObjectsController.addMovable(bullet);
        }
    }

    private static boolean created(Bullet bullet) {
        return bullet != null;
    }
}
