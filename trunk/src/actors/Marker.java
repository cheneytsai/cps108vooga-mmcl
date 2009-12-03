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
    private static int NumberOfMarkers = 0;

    public Marker(String image, Dimension size, Point position, GameModel model) {
        super(image, size, position, model);
        NumberOfMarkers++;
    }

    @Override
    protected void loadBehavior() {
        // Markers don't do anything; they are placeholders

    }

    public void remove() {
        NumberOfMarkers--;
        super.remove();
    }
}
