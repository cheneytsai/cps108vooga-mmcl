package actions;

import util.reflection.Reflection;
import model.GameModel;
import actors.Actor;

/**
 * 
 * @author meganheysham
 *
 */
public class Add implements Action
{
    GameModel myModel;
    String myActorType;

    public Add(GameModel model, String actor) {
        myModel = model;
        myActorType = actor;
    }
    
    public void execute(Actor... actors)
    {
        myModel.addActor((Actor) Reflection.createInstance(myActorType,actors[0].getPosition(),myModel));        
    }

}
