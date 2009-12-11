package actions;

import physics.PhysicsVector;
import actors.Actor;

/**
 * Resets the magnitude of an actor's velocity.
 * 
 * @author Megan Heysham
 * 
 */

public class ChangeSpeed implements Action
{
    private double mySpeed;

    public ChangeSpeed(double s)
    {
        mySpeed = s;
    }

    public void execute(Actor... actors)
    {
        actors[0].setVelocity(new PhysicsVector(actors[0].getVelocity()
                .getDirection(), mySpeed));

    }

}
