package actions;

import actors.*;

/**
 * An action to be called on an arbitrary number of actors.
 * 
 * @author Michael Yu
 * 
 */
public interface Action
{

    /**
     * Execute associated Action on given Actor The Actor calling the Action
     * should be the first argument
     * 
     * @param a
     */
    public void execute(Actor... actors);

}
