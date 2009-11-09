package actions;

import actors.Actor;

public class Move implements Action {
    
    Direction myDirection;
    int myMagnitude;
    
    public Move(Direction d, int magnitude)
    {
        myDirection = d;
        myMagnitude = magnitude;
    }
    
    @Override
    public void execute(Actor a) {

    }

}
