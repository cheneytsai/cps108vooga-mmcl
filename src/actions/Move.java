package actions;

import java.awt.Point;

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
    public void execute(Actor...a) {
       Point original = a[0].getPosition();
       a[0].setPosition(new Point((int)(original.x + myDirection.xShift()*myMagnitude), (int)(original.y + myDirection.yShift()*myMagnitude)));
    }

}
