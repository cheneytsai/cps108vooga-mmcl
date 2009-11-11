package actions;


import java.awt.Point;
import java.awt.geom.Rectangle2D;
import actors.Actor;
import actors.PhysicsVector;

public class Bounce implements Action {

    public void execute(Actor... actors) {
        Rectangle2D a = actors[0].getShape().getBounds2D();
        Rectangle2D b = actors[1].getShape().getBounds2D();
        PhysicsVector v = actors[0].getVelocity();
        Direction d = v.getDirection();
        
        if (a.contains(new Point((int)b.getMinX(), (int)b.getCenterY())) 
                || a.contains(new Point((int)b.getMaxX(), (int)b.getCenterY())))
        {
            actors[0].setVelocity(new PhysicsVector(new Direction(d.xShift()*-1, d.yShift()), v.getMagnitude()));
        }
        
        if (a.contains(new Point((int)b.getCenterX(), (int)b.getMinY())) 
                || a.contains(new Point((int)b.getCenterX(), (int)b.getMaxY())))
        {
            actors[0].setVelocity(new PhysicsVector(new Direction(d.xShift(), d.yShift()*-1), v.getMagnitude()));
        }
        

    }
    

}
