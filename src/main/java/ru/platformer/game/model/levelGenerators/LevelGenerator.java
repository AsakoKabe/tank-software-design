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
}
