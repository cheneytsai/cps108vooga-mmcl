package actions;

import java.awt.Point;
import actors.Actor;

/**
 * 
 * Rotates an actor by a given number of degrees clockwise.
 * 
 * @author Megan Heysham
 * @author Michael Yu
 * 
 */
public class Rotate implements Action
{

    private int myDegree;

    public Rotate(int degree)
    {
        myDegree = degree;

    }

    public void execute(Actor... actors)
    {
        int height = (int) actors[0].getSize().getHeight();
        int width = (int) actors[0].getSize().getWidth();

        Actor a = actors[0];
        double d = a.getHeading();
        a.setHeading(d + Math.toRadians(myDegree));
        if (height > width)
            actors[0].setPosition(new Point(actors[0].getPosition().x + 13,
                    actors[0].getPosition().y));
        if (height < width)
            actors[0].setPosition(new Point(actors[0].getPosition().x - 13,
                    actors[0].getPosition().y));
    }

}
