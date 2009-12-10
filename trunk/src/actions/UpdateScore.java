package actions;

import actors.Actor;

/**
 * 
 * @author Michael Yu
 * 
 */
public class UpdateScore implements Action
{

    private int myIncrement;

    public UpdateScore(int increment)
    {
        myIncrement = increment;
    }

    public void execute(Actor... actors)
    {
        actors[0].getModel().updateScore(myIncrement);

    }

}
