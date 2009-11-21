package actions;

import actors.Actor;
import actors.PhysicsVector;

public class ChangeSpeed implements Action{
    private double mySpeed;
    
    public ChangeSpeed(double s)
    {
        mySpeed = s;
    }
    
    
    public void execute(Actor... actors) {
        actors[0].setVelocity(new PhysicsVector(actors[0].getVelocity().getDirection(), mySpeed));
        
    }

}
