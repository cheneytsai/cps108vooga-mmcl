package actions;

import actors.Actor;

/**
 * 
 * Quits the program.
 * 
 * @author Megan Heysham
 * 
 */

public class Quit implements Action
{

    public void execute(Actor... actors)
    {
        System.exit(0);
    }

}
