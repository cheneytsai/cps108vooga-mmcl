package actions;

import gameengine.GameModel;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import util.reflection.Reflection;

import conditions.NumberOf;

import actors.Actor;

/**
 * Randomly adds a new Actor to a given GameModel.
 * 
 * @author Megan Heysham
 * 
 */

public class AddPiece implements Action
{

    private static ResourceBundle myPieceResources;
    private static Actor myNext; // The next Actor to be added; for the purposes
    // of drawing it before it's added
    private static GameModel myModel;

    public AddPiece(String gameName, GameModel model)
    {
        if (myPieceResources == null)
            myPieceResources = ResourceBundle.getBundle("resources." + gameName
                    + "Pieces");
        myModel = model;
    }

    /**
     * Add the next Actor if it has one. Then creates a new next. If there was
     * no next Actor, it creates one, then calls itself.
     */
    public void execute(Actor... actors)
    {

        if (new NumberOf(myModel, myPieceResources.getString("PieceClass"), 0)
                .evaluate())
        {
            if (myNext == null)
            {
                myNext = createRandom();
                execute();

            } else
            {
                myModel.addActor(myNext);
                myNext = createRandom();
            }

        }
    }

    /**
     * Randomly creates a new actor from the resource file
     */
    private static Actor createRandom()
    {

        String number = myPieceResources.getString("NumberOfPieces");
        int random = myModel.getRandom().nextInt(Integer.parseInt(number));
        String image = myPieceResources.getString("" + random);
        Image newImage = new ImageIcon(image).getImage();
        Dimension size = new Dimension(newImage.getWidth(null), newImage
                .getHeight(null));
        if (size.width % 52 == 0)
            return (Actor) Reflection.createInstance(myPieceResources
                    .getString("PieceClass"), image, size, new Point(493,
                    size.height / 2), myModel);
        else
            return (Actor) Reflection.createInstance(myPieceResources
                    .getString("PieceClass"), image, size, new Point(480,
                    size.height / 2), myModel);
    }

    /**
     * 
     * @return The image of the next Actor to be added, so that it can be drawn
     *         by the LevelViewer before it is actually added.
     */
    public static Image nextImage()
    {
        if (myNext == null)
        {
            myNext = createRandom();
        }
        return new ImageIcon(myNext.getImageString()).getImage();
    }

    public static void clearNext()
    {
        myNext = null;
    }

}
