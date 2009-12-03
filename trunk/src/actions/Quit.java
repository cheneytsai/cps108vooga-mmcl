package actions;

import actors.Actor;

/**
 * 
 * @author meganheysham
 *
 */

public class Quit implements Action {

    public void execute(Actor... actors) {
        System.exit(0);
    }

}
