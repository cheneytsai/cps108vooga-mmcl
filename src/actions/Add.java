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
    String myActorType;
    GameModel myModel;
    
    public Add(GameModel model, String actor) {
        myActorType = actor;
        myModel = model;
    }
    
    public void execute(Actor... actors)
    {
        myModel.addActor((Actor) Reflection.createInstance(myActorType,actors[0].getPosition(),myModel));        
    }

}
