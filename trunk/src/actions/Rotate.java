package actions;

import java.awt.Point;
import java.util.ResourceBundle;

import actors.Actor;

/**
 * 
 * @author Megan Heysham
 * 
 */
public class Rotate implements Action
{

    private ResourceBundle myRotations;

    public Rotate(String gameName)
    {
        myRotations = ResourceBundle.getBundle("resources." + gameName
                + "Rotation");
    }

    public void execute(Actor... actors)
    {
        String newImage = myRotations.getString(actors[0].getImageString());
        actors[0].setImage(newImage);
        actors[0].setImage(newImage);
        int height = (int) actors[0].getSize().getHeight();
        int width = (int) actors[0].getSize().getWidth();
        actors[0].setSize(height, width);
        if (height > width)
            actors[0].setPosition(new Point(actors[0].getPosition().x + 13,
                    actors[0].getPosition().y));
        if (height < width)
            actors[0].setPosition(new Point(actors[0].getPosition().x - 13,
                    actors[0].getPosition().y));
    }

}
