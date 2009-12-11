package actions;

import physics.Direction;
import physics.PhysicsVector;
import gameengine.GameModel;
import util.reflection.Reflection;
import actors.Actor;

/**
 * Adds a given type of Actor to a given GameModel
 * 
 * @author Megan Heysham
 * 
 */
public class Add implements Action
{
    private String myActorType;
    private GameModel myModel;

    public Add(GameModel model, String actor)
    {
        myActorType = actor;
        myModel = model;
    }

    public void execute(Actor... actors)
    {
        myModel.addActor((Actor) Reflection.createInstance(myActorType,
                actors[0].getPosition(), myModel, new PhysicsVector(
                        new Direction(-1, -1), 10)));
    }

}
