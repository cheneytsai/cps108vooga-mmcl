package actions;

import actors.Actor;

public class Remove implements Action {

    public void execute(Actor... actors) {
        actors[0].remove();
    }


}
