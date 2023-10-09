package ru.platformer.game.model.entityFiller;

import com.badlogic.gdx.math.GridPoint2;
import ru.platformer.game.entityControllers.AIController;
import ru.platformer.game.entityControllers.PlayerController;
import ru.platformer.game.graphics.LevelGraphics;
import ru.platformer.game.graphics.ObstacleGraphics;
import ru.platformer.game.graphics.TankGraphics;
import ru.platformer.game.model.Level;
import ru.platformer.game.model.Obstacle;
import ru.platformer.game.model.Tank;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileFilling implements FillingStrategy{
    private final String fileName;
    private Level level;
    private LevelGraphics levelGraphics;
    private PlayerController playerController;

    public FileFilling(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void createEntities(Level level, LevelGraphics levelGraphics, PlayerController playerController, AIController aiController) {
        this.level = level;
        this.levelGraphics = levelGraphics;
        this.playerController = playerController;

        readFile();
    }

    private void readFile() {
        int yCoordinate = 6;
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

    private void parseLine(String line, int yCoordinate){
        for (int i = 0; i < line.length(); i++){
            char c = line.charAt(i);
            switch (c){
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
        ObstacleGraphics obstacleGraphics = new ObstacleGraphics(
                "images/greenTree.png", obstacle, levelGraphics.getGroundLayer()
        );
        if (level.collisionExist(obstacle.getCurrentCoordinates())){
            return;
        }
        level.addGameEntity(obstacle);
        levelGraphics.addEntityGraphics(obstacleGraphics);
    }

    private void createPlayer(int xCoordinate, int yCoordinate){
        Tank tank = new Tank(new GridPoint2(xCoordinate, yCoordinate));

        TankGraphics tankGraphics = new TankGraphics("images/tank_blue.png", tank, levelGraphics.getTileMovement());
        level.addGameEntity(tank);
        levelGraphics.addEntityGraphics(tankGraphics);
        playerController.addGameEntity(tank);
    }

}
