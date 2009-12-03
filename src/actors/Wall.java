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
    private static int numberOfWalls = 0;

    public Wall(String string, Dimension dimension, Point point,
            GameModel gameModel) {
        super(string, dimension, point, gameModel);
        numberOfWalls++;
    }

    @Override
    protected void loadBehavior() {
        myDefaultBehavior = null;
    }

    public void remove() {
        numberOfWalls--;
        super.remove();
    }
}
