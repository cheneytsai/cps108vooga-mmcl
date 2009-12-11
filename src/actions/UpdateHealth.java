package actions;

import gameengine.GameModel;
import actors.Actor;

/**
 * Increments the health of an Actor by a given amount. Removes the Actor if its
 * health has been depleted.
 * 
 * @author Lisa Gutermuth
 * 
 */

public class UpdateHealth implements Action
{
    private int myIncrement;
    private GameModel myModel;

    public UpdateHealth(GameModel model, int increment)
    {
        myModel = model;
        myIncrement = increment;
    }

    public void execute(Actor... actors)
    {
        actors[0].setHealth(actors[0].getHealth() + myIncrement);

        if (actors[0].getHealth() <= 0)
        {
            new Remove().execute(actors);
            if (actors[0].getClass().getCanonicalName().equals("actors.Brick"))
            {
                new RandomAdd(myModel).execute(actors);
            }
        }

    }

}
