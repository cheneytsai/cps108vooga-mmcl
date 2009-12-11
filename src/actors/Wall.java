//TODO: Remove wall classes and replace them with boundary behaviors in all Actor classes
package actors;

import gameengine.GameModel;

import java.awt.Dimension;
import java.awt.Point;

import physics.Direction;
import physics.PhysicsVector;

/**
 * 
 * @author Lisa Gutermuth
 * 
 */
public class Wall extends Actor
{

    public Wall(String string, Dimension dimension, Point point,
            GameModel gameModel)
    {
        super(string, dimension, point, gameModel, new PhysicsVector(new Direction(0,0),0));
    }

    @Override
    protected void loadBehavior()
    {
        myDefaultBehavior = null;
    }
}
