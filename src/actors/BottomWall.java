package actors;

import gameengine.GameModel;

import java.awt.Dimension;
import java.awt.Point;
import actions.Direction;

/**
 * 
 * @author Lisa Gutermuth
 * 
 */
public class BottomWall extends Actor
{

//    public BottomWall(String string, Dimension dimension, Point point,
//            GameModel gameModel)
//    {
//
//        super(string, dimension, point, gameModel);
//    }
    
    public BottomWall(String image, Dimension size, Point position, GameModel model, PhysicsVector velocity) {
        super(image,size,position,model,velocity);
    }

    @Override
    protected void loadBehavior()
    {
        myDefaultBehavior = null;

    }

}
