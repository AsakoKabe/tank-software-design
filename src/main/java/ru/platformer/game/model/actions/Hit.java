package ru.platformer.game.model.actions;

import ru.platformer.game.Action;
import ru.platformer.game.GameObject;
import ru.platformer.game.model.Healthable;
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
        System.out.println(damagedObject.toString());
        level.deleteGameObject(bullet);
        if (damagedObject instanceof Healthable){
            Healthable healthable = (Healthable) damagedObject;
            healthable.updateHealth(-bullet.getDamage());
            if (healthable.getHealthPoint() == 0){
                System.out.println("delete tank from level ");
                level.deleteGameObject(damagedObject);
            }
        }
    }
}
