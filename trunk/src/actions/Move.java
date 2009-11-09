package actions;

import java.awt.Point;

import actors.Actor;

public class Move implements Action {
    
    Direction myDirection;
    double myMagnitude;
    
    public Move(Direction d, double e)
    {
        myDirection = d;
        myMagnitude = e;
    }
    
    @Override
    public void execute(Actor...a) {
       Point original = a[0].getPosition();
       a[0].setPosition(new Point((int)(original.x + myDirection.xShift()*myMagnitude), (int)(original.y + myDirection.yShift()*myMagnitude)));
    }

}
