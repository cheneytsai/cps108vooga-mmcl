package actors;

import gameengine.GameModel;

import java.awt.Dimension;
import java.awt.Point;

import physics.PhysicsVector;

/**
 * 
 * @author Lisa Gutermuth
 * 
 */
public class BottomWall extends Actor
{
    public BottomWall(String image, Dimension size, Point position,
            GameModel model, PhysicsVector velocity)
    {
        super(image, size, position, model, velocity);
    }

    @Override
    protected void loadBehavior()
    {
        myDefaultBehavior = null;

    }

}
