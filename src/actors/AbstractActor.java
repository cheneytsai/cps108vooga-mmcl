package actors;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

public abstract class AbstractActor {
    // state variables
    protected Point myCenter;
    protected Point myVelocity;
    protected Dimension mySize;
    protected Color myColor;
    protected double myHeading;

    // flags
    protected boolean hasChanged;

    /*
     * Constructs a actor at a given position. Also, define its initial
     * properties
     */
    public AbstractActor(Point center, Dimension size, Point velocity,
            Color color) {
        // temp
    }
}
