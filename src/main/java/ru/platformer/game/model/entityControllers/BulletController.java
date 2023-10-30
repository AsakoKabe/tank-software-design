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

    public void addBullet(Bullet bullet) {
        bullets.add(bullet);
    }

    @Override
    public ArrayList<Action> generateActions() {
        ArrayList<Action> actions = new ArrayList<>();
        actions.addAll(createHitActions());
        actions.addAll(createMoveActions());

        return actions;
    }

    private List<Action> createHitActions() {
        Map<Bullet, GameObject> damagedObjects = createDamagedObjects();
        List<Action> actions = new ArrayList<>();
        for (Bullet bullet : damagedObjects.keySet()) {
            actions.add(createHitAction(bullet, damagedObjects));
        }
        return actions;
    }

    private Hit createHitAction(Bullet bullet, Map<Bullet, GameObject> damagedObjects) {
        return new Hit(level, bullet, damagedObjects.get(bullet));
    }

    private HashMap<Bullet, GameObject> createDamagedObjects() {
        HashMap<Bullet, GameObject> damagedObjects = new HashMap<>();
        bullets.forEach(
                bullet -> {
                    if (isHit(bullet)) {
                        damagedObjects.put(
                                bullet,
                                getDamagedObject(bullet)
                        );
                    }
                }
        );

        return damagedObjects;
    }

    private GameObject getDamagedObject(Bullet bullet) {
        return collisionDetector.getObjectByCoordinates(bullet.getDestinationCoordinates());
    }

    private boolean isHit(Bullet bullet) {
        return collisionDetector.collisionExist(bullet.getDestinationCoordinates());
    }

    private ArrayList<Action> createMoveActions() {
        return bullets.stream().map(
                bullet -> new MoveAction(
                        new OverlappingMove(bullet.getDirection(), collisionDetector,
                                level.getWidth(), level.getHeight()
                        ),
                        bullet
                )
        ).collect(Collectors.toCollection(ArrayList::new));
    }
}
