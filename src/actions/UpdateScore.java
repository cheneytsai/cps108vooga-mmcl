package actions;

import gameengine.GameModel;
import actors.Actor;

/**
 * 
 * @author Michael Yu
 * 
 */
public class UpdateScore implements Action
{

    private int myIncrement;
    private GameModel myModel;

    public UpdateScore(int increment, GameModel model)
    {
        myIncrement = increment;
        myModel = model;
        
    }

    public void execute(Actor... actors)
    {
        myModel.updateScore(myIncrement);

    }

}
