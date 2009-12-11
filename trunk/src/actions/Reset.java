package actions;

import java.awt.Point;

import physics.Direction;
import physics.PhysicsVector;
import actors.Actor;

/**
 * Changes the position and velocity of an Actor to a default.
 * 
 * This is currently only called using Ball and has not been generalized.
 * 
 * @author Lisa Gutermuth
 *
 */

public class Reset implements Action
{

    public void execute(Actor... actors)
    {
        actors[0].setPosition(new Point(480, 450));
        actors[0].setVelocity(new PhysicsVector(new Direction(-1, -1), 10));

    }

}
