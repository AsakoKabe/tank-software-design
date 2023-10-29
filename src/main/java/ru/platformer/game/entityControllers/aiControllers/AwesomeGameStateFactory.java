package ru.platformer.game.entityControllers.aiControllers;

import org.awesome.ai.state.GameState;
import org.awesome.ai.state.immovable.Obstacle;
import org.awesome.ai.state.movable.Actor;
import org.awesome.ai.state.movable.Bot;
import org.awesome.ai.state.movable.Orientation;
import org.awesome.ai.state.movable.Player;
import ru.platformer.game.Direction;
import ru.platformer.game.GameObject;
import ru.platformer.game.model.objects.Tank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AwesomeGameStateFactory {
    private final List<Tank> tanksGameObject;
    private final Tank playerGameObject;
    private final List<ru.platformer.game.model.objects.Obstacle> obstaclesGameObject;
    private final int levelWidth;
    private final int levelHeight;
    private final HashMap<Direction, Orientation> orientationByRotation = new HashMap<>();
    private final HashMap<Actor, GameObject> gameObjectToActor = new HashMap<>();

    public AwesomeGameStateFactory(
            List<Tank> bots,
            Tank player,
            List<ru.platformer.game.model.objects.Obstacle> obstacles,
            int levelWidth, int levelHeight
    ) {
        this.tanksGameObject = bots;
        this.playerGameObject = player;
        this.obstaclesGameObject = obstacles;
        this.levelWidth = levelWidth;
        this.levelHeight = levelHeight;

        createMappingDirectionToOrientation();
    }

    public GameObject getGameObjectByActor(Actor actor){
        return gameObjectToActor.get(actor);
    }

    public GameState create(){
        List<Obstacle> obstaclesAwesome = createAwesomeObstacles();
        List<Bot> botsAwesome = createAwesomeBots();
        Player playerAwesome = createPlayerAwesome();

        return GameState.builder()
                .obstacles(obstaclesAwesome)
                .bots(botsAwesome)
                .player(playerAwesome)
                .levelWidth(levelWidth)
                .levelHeight(levelHeight)
                .build();
    }

    private Player createPlayerAwesome() {
        Player playerActor = new Player.PlayerBuilder()
                .x(playerGameObject.getCurrentCoordinates().x)
                .y(playerGameObject.getCurrentCoordinates().y).
                destX(playerGameObject.getDestinationCoordinates().x)
                .destY(playerGameObject.getDestinationCoordinates().y)
                .orientation(getOrientationByDirection(playerGameObject.getDirection()))
                .build();
        gameObjectToActor.put(playerActor, playerGameObject);

        return playerActor;
    }

    private List<Bot> createAwesomeBots() {
        List<Bot> botsAwesome = new ArrayList<>();
        tanksGameObject.forEach(tank -> {
            Bot botActor = new Bot.BotBuilder()
                    .x(tank.getCurrentCoordinates().x)
                    .y(tank.getCurrentCoordinates().y).
                    destX(tank.getDestinationCoordinates().x)
                    .destY(tank.getDestinationCoordinates().y)
                    .orientation(getOrientationByDirection(tank.getDirection()))
                    .build();
            botsAwesome.add(botActor);
            gameObjectToActor.put(botActor, tank);
        });

        return botsAwesome;

    }

    private List<org.awesome.ai.state.immovable.Obstacle> createAwesomeObstacles() {
        //    В recomendations лежат только Actor, Obstacle к ним не относится
        //    поэтому не добавляем в  gameObjectToActor

        List<org.awesome.ai.state.immovable.Obstacle> obstaclesAwesome = new ArrayList<>();
        obstaclesGameObject.forEach(obstacle -> {
            Obstacle obstacleActor = new org.awesome.ai.state.immovable.Obstacle(
                    obstacle.getCurrentCoordinates().x,
                    obstacle.getCurrentCoordinates().y
            );
            obstaclesAwesome.add(obstacleActor);
        });
        return obstaclesAwesome;
    }


    private Orientation getOrientationByDirection(Direction direction){
        return orientationByRotation.get(direction);
    }

    private void createMappingDirectionToOrientation() {
        orientationByRotation.put(Direction.UP, Orientation.N);
        orientationByRotation.put(Direction.LEFT, Orientation.W);
        orientationByRotation.put(Direction.DOWN, Orientation.S);
        orientationByRotation.put(Direction.RIGHT, Orientation.E);
    }


}
