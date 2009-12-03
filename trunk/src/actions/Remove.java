package actions;

import actors.Actor;
/**
 * 
 * @author Michael Yu
 *
 */
public class Remove implements Action {

    public void execute(Actor... actors) {
        actors[0].remove();
    }


}
