package actors;

import gameengine.GameModel;

import java.awt.Dimension;
import java.awt.Point;

import physics.Direction;
import physics.PhysicsVector;

/**
 * 
 * @author Megan Heysham
 * 
 */
public class Block extends Actor
{

    public Block(String image, Dimension size, Point position, GameModel model,
            PhysicsVector velocity)
    {
        super(image, size, position, model, velocity);
        setVelocity(new PhysicsVector(new Direction(1, 1), 0));
    }

    @Override
    protected void loadBehavior()
    {
        myDefaultBehavior = null;

    }

}
