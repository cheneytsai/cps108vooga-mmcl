package actions;

import java.awt.Point;

import actors.Actor;
import actors.PhysicsVector;

/**
 * 
 * @author Michael Yu
 *
 */
public class Move implements Action {
    
    private PhysicsVector myVelocity;
    
    public Move(PhysicsVector v)
    {
        myVelocity = v;
    }
    
    public void execute(Actor...a) {
        Point original = a[0].getPosition();
        Direction myDirection = myVelocity.getDirection();
        double myMagnitude = myVelocity.getMagnitude();
        a[0].setPosition(new Point((int)(original.x + myDirection.xShift()*myMagnitude), (int)(original.y + myDirection.yShift()*myMagnitude)));
    }

}
