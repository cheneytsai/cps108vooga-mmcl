package actions;

import gameengine.GameModel;
import util.reflection.Reflection;
import actors.Actor;

/**
 * 
 * @author Megan Heysham
 * 
 */
public class Add implements Action {
    String myActorType;
    GameModel myModel;

    public Add(GameModel model, String actor) {
        myActorType = actor;
        myModel = model;
    }

    public void execute(Actor... actors) {
        myModel.addActor((Actor) Reflection.createInstance(myActorType,
                actors[0].getPosition(), myModel));
    }

}
