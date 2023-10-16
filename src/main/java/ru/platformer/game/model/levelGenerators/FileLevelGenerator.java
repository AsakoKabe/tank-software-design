package ru.platformer.game.model.levelGenerators;

import com.badlogic.gdx.math.GridPoint2;
import org.javatuples.Pair;
import org.javatuples.Triplet;
import ru.platformer.game.GameObject;
import ru.platformer.game.model.Level;
import ru.platformer.game.model.LevelListener;
import ru.platformer.game.model.Obstacle;
import ru.platformer.game.model.Tank;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

public class FileLevelGenerator implements LevelGenerator {
    private final String fileName;
    private final ArrayList<LevelListener> levelListeners;
    private Level level;
    private GameObject playerGameObject;
    private final ArrayList<GameObject> aiGameObjects = new ArrayList<>();

    private int maxY;
    private int maxX;

    public FileLevelGenerator(ArrayList<LevelListener> levelListeners, String fileName) {
        this.levelListeners = levelListeners;
        this.fileName = fileName;
    }

    @Override
    public Triplet<Level, GameObject, ArrayList<GameObject>> generate() {
        level = new Level(levelListeners);

        generateFromFile();
        LevelGenerator.initBorder(level, maxX, maxY);

        return new Triplet<>(level, playerGameObject, aiGameObjects);
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
                    createTree(i, yCoordinate);
                    break;
                case 'X':
                    createPlayer(i, yCoordinate);
            }
        }
    }

    private void createTree(int xCoordinate, int yCoordinate) {
        Obstacle obstacle = new Obstacle(new GridPoint2(xCoordinate, yCoordinate));
        if (level.collisionExist(obstacle.getCurrentCoordinates())) {
            return;
        }
        level.addGameObject(obstacle);
    }

    private void createPlayer(int xCoordinate, int yCoordinate) {
        playerGameObject = new Tank(new GridPoint2(xCoordinate, yCoordinate));
        level.addGameObject(playerGameObject);
    }

}
