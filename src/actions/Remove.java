package actions;

import actors.Actor;

public class Remove implements Action {

    @Override
    public void execute(Actor... actors) {
        for (Actor a : actors)
            a.remove();

    }

}
