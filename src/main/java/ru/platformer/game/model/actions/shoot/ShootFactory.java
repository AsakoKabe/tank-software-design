package ru.platformer.game.model.actions.shoot;

import ru.platformer.game.Action;
import ru.platformer.game.ActionFactory;
import ru.platformer.game.GameObject;
import ru.platformer.game.model.entityControllers.IndependentMovableObjectsController;
import ru.platformer.game.model.objects.Level;
import ru.platformer.game.model.Shooter;

public class ShootFactory implements ActionFactory {
    private final Level level;
    private IndependentMovableObjectsController independentMovableObjectsController;

    public ShootFactory(Level level, IndependentMovableObjectsController independentMovableObjectsController) {
        this.level = level;
        this.independentMovableObjectsController = independentMovableObjectsController;
    }

    @Override
    public Action create(GameObject gameObject) {
        return new ShootAction((Shooter) gameObject, level, independentMovableObjectsController);
    }
}
