package actions;

import gameengine.GameModel;
import actors.Actor;

/**
 * 
 * @author Michael Yu
 * 
 */
public class UpdateScore implements Action {

    private GameModel myModel;
    private int myIncrement;

    public UpdateScore(GameModel model, int increment) {
        myModel = model;
        myIncrement = increment;
    }

    public void execute(Actor... actors) {
        myModel.updateScore(myIncrement);

    }

}
