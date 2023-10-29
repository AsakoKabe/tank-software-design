package ru.platformer.game.model.entityControllers;

import ru.platformer.game.Action;
import ru.platformer.game.ActionFactory;
import ru.platformer.game.EntityController;
import ru.platformer.game.model.CollisionDetector;
import ru.platformer.game.model.Movable;
import ru.platformer.game.model.actions.move.MoveAction;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class IndependentMovableObjectsController implements EntityController {
    private final CollisionDetector collisionDetector;
    private final List<Movable> movables = new ArrayList<>();

    public IndependentMovableObjectsController(CollisionDetector collisionDetector) {
        this.collisionDetector = collisionDetector;
    }

    public void addMovable(Movable movable){
        movables.add(movable);
    }
    @Override
    public ArrayList<Action> generateActions() {
        return movables.stream().map(
                movable -> new MoveAction(
                        movable.getDirection(), collisionDetector, movable
                )
        ).collect(Collectors.toCollection(ArrayList::new));
    }
}
