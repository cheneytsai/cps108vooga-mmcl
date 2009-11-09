package actors;

import actions.Direction;

public class PhysicsVector {
    private Direction myDirection;
    private double myMagnitude;
    
    public PhysicsVector(Direction d, double magnitude)
    {
        myDirection = d;
        myMagnitude = magnitude;
    }
    
    public Direction getDirection()
    {
        return myDirection;
    }
    
    public double getMagnitude()
    {
        return myMagnitude;
    }
}
