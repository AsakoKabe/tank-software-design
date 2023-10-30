package ru.platformer.game.model.actions;

import ru.platformer.game.Action;
import ru.platformer.game.GameObject;
import ru.platformer.game.model.objects.Bullet;
import ru.platformer.game.model.objects.Level;

public class Hit implements Action {
    private final GameObject damagedObject;
    private final Bullet bullet;
    private final Level level;

    public Hit(Level level, Bullet bullet, GameObject damagedObject) {
        this.damagedObject = damagedObject;
        this.bullet = bullet;
        this.level = level;
    }

    @Override
    public void apply() {
        level.deleteGameObject(bullet);
    }
}