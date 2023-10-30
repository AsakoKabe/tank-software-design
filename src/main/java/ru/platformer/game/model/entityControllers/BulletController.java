package ru.platformer.game.model.entityControllers;

import ru.platformer.game.Action;
import ru.platformer.game.EntityController;
import ru.platformer.game.GameObject;
import ru.platformer.game.model.CollisionDetector;
import ru.platformer.game.model.actions.Hit;
import ru.platformer.game.model.actions.move.MoveAction;
import ru.platformer.game.model.actions.move.OverlappingMove;
import ru.platformer.game.model.objects.Bullet;
import ru.platformer.game.model.objects.Level;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BulletController implements EntityController {
    private final CollisionDetector collisionDetector;
    private final List<Bullet> bullets = new ArrayList<>();
    private final Level level;

    public BulletController(CollisionDetector collisionDetector, Level level) {
        this.collisionDetector = collisionDetector;
        this.level = level;
    }

    public boolean addBullet(Bullet bullet) {
        if (isInstantHit(bullet)) return false;

        bullets.add(bullet);
        return true;
    }

    private boolean isInstantHit(Bullet bullet) {
        if (collisionDetector.collisionExist(bullet.getCurrentCoordinates())){
            createHitAction(
                    bullet,
                    collisionDetector.getObjectByCoordinates(bullet.getCurrentCoordinates())).apply();
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<Action> generateActions() {
        ArrayList<Action> actions = new ArrayList<>();
        actions.addAll(createHitActions());
        actions.addAll(createMoveActions());

        return actions;
    }

    private List<Action> createHitActions() {
        Map<Bullet, GameObject> damagedObjects = mapDamagedObjects();

        List<Action> actions = new ArrayList<>();
        for (Bullet bullet : damagedObjects.keySet()) {
            actions.add(createHitAction(bullet, damagedObjects.get(bullet)));
        }
        return actions;
    }

    private Hit createHitAction(Bullet bullet, GameObject damagedObjects) {
        return new Hit(level, bullet, damagedObjects);
    }

    private HashMap<Bullet, GameObject> mapDamagedObjects() {
        HashMap<Bullet, GameObject> damagedObjects = new HashMap<>();
        List<Bullet> deletedBullets = new ArrayList<>();

        for (Bullet bullet : bullets) {
            GameObject damageObject = getDamagedObject(bullet);
            if (isHit(bullet, damageObject)) {
                damagedObjects.put(bullet, damageObject);
                deletedBullets.add(bullet);
            }
        }

        deletedBullets.forEach(bullets::remove);

        return damagedObjects;
    }


    private boolean isHit(Bullet bullet, GameObject damageObject) {
        return collisionDetector.collisionExist(bullet.getDestinationCoordinates()) &&
                damageObject != null &&
                damageObject != bullet;
    }

    private GameObject getDamagedObject(Bullet bullet) {
        return collisionDetector.getObjectByCoordinates(bullet.getDestinationCoordinates());
    }

    private ArrayList<Action> createMoveActions() {
        return bullets.stream().map(
                bullet -> new MoveAction(
                        new OverlappingMove(
                                bullet.getDirection(), level.getWidth(), level.getHeight()
                        ),
                        bullet
                )
        ).collect(Collectors.toCollection(ArrayList::new));
    }
}
