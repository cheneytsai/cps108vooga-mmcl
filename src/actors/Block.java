package actors;

import gameengine.GameModel;

import java.awt.Dimension;
import java.awt.Point;

/**
 * 
 * @author Megan Heysham
 * 
 */
public class Block extends Actor
{

    public Block(String image, Dimension size, Point position, GameModel model)
    {
        super(image, size, position, model);
    }

    @Override
    protected void loadBehavior()
    {
        // Blocks sit and do nothing

    }

}
