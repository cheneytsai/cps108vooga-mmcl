package actors;

import java.awt.Dimension;
import java.awt.Point;

import model.GameModel;
/**
 * Until we have directional collision detection, this is our way of knowing
 * which way things should go when they hit side walls.
 * 
 * @author Megan Heysham
 *
 */
public class LeftWall extends Wall {
    private static int numberOfLeftWalls = 0;
    public LeftWall(String string, Dimension dimension, Point point,
            GameModel gameModel) 
    {
        
        super(string, dimension, point, gameModel);
        numberOfLeftWalls++;
    }
    public void remove()
    {
    numberOfLeftWalls--;
     super.remove();
    }
}
