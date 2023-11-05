package ru.platformer.game.model.actions.shoot;

import ru.platformer.game.Action;
import ru.platformer.game.model.actions.ActionFactory;
import ru.platformer.game.GameObject;
import ru.platformer.game.model.objects.Level;
import ru.platformer.game.model.Shooter;

public class ShootFactory implements ActionFactory {
    private final Level level;

    public ShootFactory(Level level) {
        this.level = level;
    }

    @Override
    public Action create(GameObject gameObject) {
        return new ShootAction((Shooter) gameObject, level);
    }
}
