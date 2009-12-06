package actors;

import java.awt.Dimension;
import java.awt.Point;

import model.GameModel;

/**
 * 
 * @author Megan Heysham
 * 
 */
public class Marker extends Actor {

    public Marker(String image, Dimension size, Point position, GameModel model) {
        super(image, size, position, model);
    }

    @Override
    protected void loadBehavior() {
        // Markers don't do anything; they are placeholders

    }

}
