package actions;

import actors.Actor;

public class Remove implements Action {

    public void execute(Actor... actors) {
//        System.out.println(actors[0].getClass().getCanonicalName());    
        actors[0].remove();
    }


}
