package actions;


import java.awt.Point;
import utilities.CollisionChecker;
import actors.Actor;
import actors.PhysicsVector;

/**
 * 
 * @author Michael Yu
 *
 */
public class Bounce implements Action {

    public void execute(Actor... actors) {
        Actor a = actors[0];
        Actor b = actors[1];
        Direction origDirection = a.getVelocity().getDirection();
        double origMagnitude = a.getVelocity().getMagnitude();
        if (CollisionChecker.intersects(b, new Point(a.getPosition().x, a.getTop())) ||
                CollisionChecker.intersects(b, new Point(a.getPosition().x, a.getBottom())))
            {
                
//                System.out.println("Y");
                a.setVelocity(new PhysicsVector(new Direction(origDirection.xShift(), origDirection.yShift()*-1), origMagnitude));
            }

            if (CollisionChecker.intersects(b, new Point(a.getLeft(), a.getPosition().y)) ||
                CollisionChecker.intersects(b, new Point(a.getRight(), a.getPosition().y)))
            {
//                System.out.println("X");
                a.setVelocity(new PhysicsVector(new Direction(origDirection.xShift()*-1, origDirection.yShift()), origMagnitude));
            }
       //TODO: Perhaps replace this action with a ReverseVelocity action, Bounce might be too specific to arkanoid. This should
            //also fix some of our collision bugs
    }
    

}
