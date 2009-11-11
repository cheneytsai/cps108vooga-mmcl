package actions;

import actors.Actor;

public class Remove implements Action {

    public void execute(Actor... actors) {
        for (Actor a : actors)
            a.remove();

    }

}
