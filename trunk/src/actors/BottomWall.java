package actors;

import java.awt.Dimension;
import java.awt.Point;
import model.GameModel;

/**
 * 
 * @author Lisa Gutermuth
 * 
 */
public class BottomWall extends Actor {

    public BottomWall(String string, Dimension dimension, Point point,
            GameModel gameModel) {

        super(string, dimension, point, gameModel);
    }

    @Override
    protected void loadBehavior() {
        myDefaultBehavior = null;

    }

}
