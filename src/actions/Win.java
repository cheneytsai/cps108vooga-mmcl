package actions;

import gameengine.GameModel;
import actors.Actor;

/**
 * Loads the win screen.
 * 
 * @author Michael Yu
 * 
 */
public class Win implements Action
{
    protected GameModel myModel;

    public Win(GameModel model)
    {
        myModel = model;
    }

    public void execute(Actor... actors)
    {
        myModel.loadEnd("Win");
    }

}
