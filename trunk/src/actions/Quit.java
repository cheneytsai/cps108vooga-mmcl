package actions;

import actors.Actor;

public class Quit implements Action {

    public void execute(Actor... actors) {
        System.exit(0);
    }

}