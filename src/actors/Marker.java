package actors;

import java.awt.Dimension;
import java.awt.Point;

import model.GameModel;

public class Marker extends Actor {
    private static int NumberOfMarkers = 0;
    public Marker(String image, Dimension size, Point position, GameModel model) {
        super(image, size, position, model);
        NumberOfMarkers++;
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void loadBehavior() {
        // TODO Auto-generated method stub

    }
    public void remove()
    {
     NumberOfMarkers--;
     super.remove();
    }
}
