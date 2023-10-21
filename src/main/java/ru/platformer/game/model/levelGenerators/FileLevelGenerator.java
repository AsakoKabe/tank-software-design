package ru.platformer.game.model.levelGenerators;

import com.badlogic.gdx.math.GridPoint2;
import org.javatuples.Triplet;
import ru.platformer.game.GameObject;
import ru.platformer.game.model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class FileLevelGenerator implements LevelGenerator {
    private final String fileName;
    private final ArrayList<LevelListener> levelListeners;
    private Level level;
    private final ArrayList<GameObject> tanks = new ArrayList<>();
    private final CollisionDetector collisionDetector;

    private int maxY;
    private int maxX;

    public FileLevelGenerator(ArrayList<LevelListener> levelListeners, CollisionDetector collisionDetector, String fileName) {
        this.levelListeners = levelListeners;
        this.collisionDetector = collisionDetector;
        this.fileName = fileName;
    }

    @Override
    public Triplet<Level, GameObject, List<GameObject>> generate() {
        level = new Level(levelListeners);
        LevelGenerator.initBorder(collisionDetector, maxX, maxY);

        generateFromFile();

        return new Triplet<>(level, tanks.get(0), tanks.subList(1, tanks.size()));
    }

    private void generateFromFile() {
        maxY = countLines();
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

    private int countLines() {
        int countLines;
        try (Stream<String> lines = Files.lines(Path.of(fileName))) {
            countLines = (int) lines.count();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return countLines;
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
        collisionDetector.addCoordinates(obstacle.getCurrentCoordinates());
    }

    private void createTank(int xCoordinate, int yCoordinate) {
        Tank tank = new Tank(new GridPoint2(xCoordinate, yCoordinate));
        tanks.add(tank);
        level.addGameObject(tank);
        collisionDetector.addCoordinates(tank.getCurrentCoordinates());
    }

}
