package actions;


import java.awt.Point;
import java.awt.geom.Rectangle2D;

import utilities.CollisionChecker;
import actors.Actor;
import actors.PhysicsVector;

public class Bounce implements Action {

    public void execute(Actor... actors) {
        Actor a = actors[0];
        Actor b = actors[1];
        Direction origDirection = a.getVelocity().getDirection();
        double origMagnitude = a.getVelocity().getMagnitude();
        if (CollisionChecker.intersects(b, new Point(a.getPosition().x, a.getTop())) ||
                CollisionChecker.intersects(b, new Point(a.getPosition().x, a.getBottom())))
            {
                
                System.out.println("Y");
                a.setVelocity(new PhysicsVector(new Direction(origDirection.xShift(), origDirection.yShift()*-1), origMagnitude));
            }

            if (CollisionChecker.intersects(b, new Point(a.getLeft(), a.getPosition().y)) ||
                CollisionChecker.intersects(b, new Point(a.getRight(), a.getPosition().y)))
            {
                System.out.println("X");
                a.setVelocity(new PhysicsVector(new Direction(origDirection.xShift()*-1, origDirection.yShift()), origMagnitude));
            }
       //TODO: Check to see if this works when bouncing off a corner
        //TODO: There must be a better way to do this. Maybe make CollisionChecker tell Actors from what side
        //the collision occurred and then have a reverse velocity action which takes an axis in its constructor?
    }
    

}
