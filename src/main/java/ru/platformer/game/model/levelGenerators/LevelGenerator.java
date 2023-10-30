package ru.platformer.game.model.levelGenerators;

import com.badlogic.gdx.math.GridPoint2;
import org.javatuples.Quartet;
import ru.platformer.game.model.CollisionDetector;
import ru.platformer.game.model.objects.Level;
import ru.platformer.game.model.objects.Obstacle;
import ru.platformer.game.model.objects.Tank;

import java.util.List;

public interface LevelGenerator {
    Quartet<Level, Tank, List<Tank>, List<Obstacle>> generate();

    static void initBorder(CollisionDetector collisionDetector, int maxX, int maxY) {
        // left right
        initAxis(collisionDetector, maxX, maxY, false);
        // top bot
        initAxis(collisionDetector, maxY, maxX, true);
    }

    private static void initAxis(CollisionDetector collisionDetector, int maxAxis1, int maxAxis2, boolean inverse) {
        for (int x : List.of(-1, maxAxis1 + 1)) {
            for (int y = 0; y < maxAxis2 + 1; y += 1) {
                GridPoint2 gridPoint2;
                if (inverse) {
                    gridPoint2 = new GridPoint2(y, x);
                } else {
                    gridPoint2 = new GridPoint2(x, y);
                }
                collisionDetector.addGameObjectByCoordinates(null, gridPoint2);
            }
        }
    }
}
