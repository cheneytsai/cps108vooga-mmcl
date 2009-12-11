package actions;

import java.awt.Point;

import physics.Direction;
import physics.PhysicsVector;
import utilities.CollisionChecker;
import actors.Actor;

/**
 * Changes the velocity of an Actor based on its collision with another Actor,
 * causing it to appear to bounce off of the other Actor.
 * 
 * @author Michael Yu
 * 
 */
public class Bounce implements Action
{

    public void execute(Actor... actors)
    {
     
        Actor a = actors[0];
        Actor b = actors[1];
        Direction origDirection = a.getVelocity().getDirection();
        double origMagnitude = a.getVelocity().getMagnitude();
        if (CollisionChecker.intersects(b, new Point(a.getPosition().x, a
                .getTop()))
                || CollisionChecker.intersects(b, new Point(a.getPosition().x,
                        a.getBottom())))
        {
            a.setVelocity(new PhysicsVector(new Direction(origDirection
                    .xShift(), origDirection.yShift() * -1), origMagnitude));
        }

        if (CollisionChecker.intersects(b, new Point(a.getLeft(), a
                .getPosition().y))
                || CollisionChecker.intersects(b, new Point(a.getRight(), a
                        .getPosition().y)))
        {
            a.setVelocity(new PhysicsVector(new Direction(origDirection
                    .xShift()
                    * -1, origDirection.yShift()), origMagnitude));
        }

    }

}
