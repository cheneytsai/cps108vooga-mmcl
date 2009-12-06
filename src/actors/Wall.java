//TODO: Remove wall classes and replace them with boundary behaviors in all Actor classes
package actors;

import java.awt.Dimension;
import java.awt.Point;
import model.GameModel;

/**
 * 
 * @author Lisa Gutermuth
 * 
 */
public class Wall extends Actor {

    public Wall(String string, Dimension dimension, Point point,
            GameModel gameModel) {
        super(string, dimension, point, gameModel);
    }

    @Override
    protected void loadBehavior() {
        myDefaultBehavior = null;
    }
}
