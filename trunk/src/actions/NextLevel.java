package actions;

import gameengine.GameModel;
import actors.Actor;

/**
 * Loads the next level in the current GameModel.
 * 
 * @author Michael Yu
 * 
 */
public class NextLevel implements Action
{

    protected GameModel myModel;

    public NextLevel(GameModel model)
    {
        myModel = model;
    }

    public void execute(Actor... actors)
    {
        myModel.loadNextLevel();

    }

}
