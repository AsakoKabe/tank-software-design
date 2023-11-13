package ru.platformer.game.model.levelGenerators;

import com.badlogic.gdx.math.GridPoint2;
import org.javatuples.Quartet;
import ru.platformer.game.model.*;
import ru.platformer.game.model.objects.Level;
import ru.platformer.game.model.objects.Obstacle;
import ru.platformer.game.model.objects.Tank;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class FileLevelGenerator implements LevelGenerator {
    public static final float SPEED = 0.5f;
    private final String fileName;
    private final ArrayList<LevelListener> levelListeners;
    private Level level;
    private final List<Tank> tanks = new ArrayList<>();
    private final List<Obstacle> obstacles = new ArrayList<>();
    private final CollisionDetector collisionDetector;

    private int maxY;
    private int maxX;

    public FileLevelGenerator(ArrayList<LevelListener> levelListeners, CollisionDetector collisionDetector, String fileName) {
        this.levelListeners = levelListeners;
        this.collisionDetector = collisionDetector;
        this.fileName = fileName;

        computeBound();
    }

    @Override
    public Quartet<Level, Tank, List<Tank>, List<Obstacle>> generate() {
        level = new Level(levelListeners, maxY, maxX);

        generateFromFile();

        return new Quartet<>(level, tanks.get(0), tanks.subList(1, tanks.size()), obstacles);
    }

    private void generateFromFile() {
        int yCoordinate = maxY;
        try {
            File file = new File(fileName);
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                parseLine(myReader.nextLine(), yCoordinate);
                yCoordinate -= 1;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
    }

    private void computeBound() {
        Path path = Path.of(fileName);
        try (Stream<String> lines = Files.lines(path)) {
            maxY = (int) lines.count();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (Stream<String> lines = Files.lines(path)) {
            maxX = lines.max(Comparator.comparingInt(String::length)).toString().length();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void parseLine(String line, int yCoordinate) {
        maxX = line.length();
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            switch (c) {
                case '_':
                    break;
                case 'T':
                    createObstacles(i, yCoordinate);
                    break;
                case 'X':
                    createTank(i, yCoordinate);
            }
        }
    }

    private void createObstacles(int xCoordinate, int yCoordinate) {
        Obstacle obstacle = new Obstacle(new GridPoint2(xCoordinate, yCoordinate));
        level.addGameObject(obstacle);
        obstacles.add(obstacle);
    }

    private void createTank(int xCoordinate, int yCoordinate) {
        Tank tank = new Tank(new GridPoint2(xCoordinate, yCoordinate), SPEED, 1, 1, level,
                collisionDetector);
        tanks.add(tank);
        level.addGameObject(tank);
    }

}
