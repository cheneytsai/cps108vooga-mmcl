package actions;

import java.awt.Point;

import physics.Direction;
import physics.PhysicsVector;

import actors.Actor;

/**
 * Moves an Actor by a given velocity.
 * 
 * @author Michael Yu
 * 
 */
public class Move implements Action
{

    private PhysicsVector myVelocity;

    public Move(PhysicsVector v)
    {
        myVelocity = v;
    }

    public void execute(Actor... a)
    {
        Point original = a[0].getPosition();
        Direction myDirection = myVelocity.getDirection();
        double myMagnitude = myVelocity.getMagnitude();
        a[0].setPosition(new Point((int) (original.x + myDirection.xShift()
                * myMagnitude), (int) (original.y + myDirection.yShift()
                * myMagnitude)));
    }

}
