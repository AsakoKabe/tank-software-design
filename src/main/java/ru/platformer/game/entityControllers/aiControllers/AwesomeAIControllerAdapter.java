package ru.platformer.game.entityControllers.aiControllers;

import org.awesome.ai.Recommendation;
import org.awesome.ai.state.GameState;
import org.awesome.ai.state.movable.Actor;
import org.awesome.ai.strategy.NotRecommendingAI;
import ru.platformer.game.AIEvent;
import ru.platformer.game.Action;
import ru.platformer.game.ActionFactory;
import ru.platformer.game.GameObject;
import ru.platformer.game.entityControllers.AIController;
import ru.platformer.game.entityControllers.EntityController;
import ru.platformer.game.model.objects.Obstacle;
import ru.platformer.game.model.objects.Tank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AwesomeAIControllerAdapter implements EntityController, AIController {
    private final NotRecommendingAI notRecommendingAI;
    private final HashMap<org.awesome.ai.Action, AIEvent> aiEventByAwesomeAction = new HashMap<>();
    private final Map<AIEvent, ActionFactory> aiEventToActionFactory = new HashMap<>();
    private final AwesomeGameStateFactory awesomeGameStateFactory;


    public AwesomeAIControllerAdapter(
            NotRecommendingAI notRecommendingAI, Tank player,
            List<Tank> bots,
            List<Obstacle> obstacles,
            int levelWidth, int levelHeight
    ) {
        this.notRecommendingAI = notRecommendingAI;
        awesomeGameStateFactory = new AwesomeGameStateFactory(bots, player, obstacles, levelWidth, levelHeight);

        createMappingAwesomeActionToAIEvent();
    }

    @Override
    public ArrayList<Action> generateActions() {
        GameState gameState = awesomeGameStateFactory.create();
        List<Recommendation> recommendations = notRecommendingAI.recommend(gameState);

        return convertRecommendationsToActions(recommendations);
    }

    private ArrayList<Action> convertRecommendationsToActions(List<Recommendation> recommendations) {
        ArrayList<Action> actions = new ArrayList<>();

        for (Recommendation recommendation: recommendations){
            Actor actor = recommendation.getActor();
            GameObject tank = awesomeGameStateFactory.getGameObjectByActor(actor);
            Action action = aiEventToActionFactory.get(
                    aiEventByAwesomeAction.get(recommendation.getAction())
            ).create(tank);

            actions.add(action);
        }
        return actions;
    }

    @Override
    public void addActionFactoryByAIEvent(AIEvent aiEvent, ActionFactory actionFactory){
        aiEventToActionFactory.put(aiEvent, actionFactory);
    }


    private void createMappingAwesomeActionToAIEvent(){
        aiEventByAwesomeAction.put(org.awesome.ai.Action.MoveEast, AIEvent.MoveRight);
        aiEventByAwesomeAction.put(org.awesome.ai.Action.MoveNorth, AIEvent.MoveUp);
        aiEventByAwesomeAction.put(org.awesome.ai.Action.MoveWest, AIEvent.MoveLeft);
        aiEventByAwesomeAction.put(org.awesome.ai.Action.MoveSouth, AIEvent.MoveDown);

    }
}
