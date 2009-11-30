package actors;

import java.awt.Dimension;
import java.awt.Point;
import model.GameModel;

public class BottomWall extends Actor
{
    public static int numberOfBottomWalls = 0;
    public BottomWall(String string, Dimension dimension, Point point,
            GameModel gameModel)
    {
        
        super(string,dimension,point,gameModel);
        numberOfBottomWalls++;
    }

    @Override
    protected void loadBehavior()
    {
        myDefaultBehavior = null;
        
    }
    public void remove()
    {
     numberOfBottomWalls--;
     super.remove();
    }
}
