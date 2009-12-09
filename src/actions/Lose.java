package actions;

import gameengine.GameModel;
import actors.Actor;

/**
 * 
 * @author Michael Yu
 * 
 */
public class Lose implements Action
{
    protected GameModel myModel;

    public Lose(GameModel model)
    {
        myModel = model;
    }

    public void execute(Actor... actors)
    {
        myModel.lose();
    }
}
