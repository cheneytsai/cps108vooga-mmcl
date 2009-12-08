package actions;

import java.awt.Point;
import actors.Actor;
import actors.PhysicsVector;

public class Reset implements Action
{

    @Override
    public void execute(Actor... actors)
    {
        actors[0].setPosition(new Point(480,450));
        actors[0].setVelocity(new PhysicsVector(new Direction(-1,-1),10));
        
    }

}
