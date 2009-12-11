package actions;

import actors.Actor;

/**
 * Removes an actor from its GameModel.
 * 
 * @author Michael Yu
 * 
 */
public class Remove implements Action
{

    public void execute(Actor... actors)
    {
        actors[0].remove();
    }

}
