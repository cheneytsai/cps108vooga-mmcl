package actions;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.util.Random;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import util.reflection.Reflection;

import conditions.NumberOf;

import model.GameModel;

import actors.Actor;

/**
 * 
 * @author Megan Heysham
 * 
 */

public class AddPiece implements Action {

    private static Random myRandom;
    private static ResourceBundle myResources;
    private GameModel myModel;

    public AddPiece(String gameName, GameModel model) {
        if (myRandom == null)
            myRandom = new Random();
        if (myResources == null)
            myResources = ResourceBundle.getBundle("resources." + gameName
                    + "Pieces");
        myModel = model;
    }

    /**
     * Randomly creates a new actor from the resource file
     */
    public void execute(Actor... actors) {

        if (myModel.gameOver()) {
            myModel.lose();
        }

        else if (new NumberOf(myModel, myResources.getString("PieceClass"), 0)
                .evaluate()) {
            String number = myResources.getString("NumberOfPieces");
            int random = myRandom.nextInt(Integer.parseInt(number));
            String image = myResources.getString("" + random);
            Image newImage = new ImageIcon(image).getImage();
            Dimension size = new Dimension(newImage.getWidth(null), newImage
                    .getHeight(null));
            if (size.width % 52 == 0)
                myModel.addActor((Actor) Reflection.createInstance(myResources
                        .getString("PieceClass"), image, size, new Point(493,
                        size.height / 2), myModel));
            else
                myModel.addActor((Actor) Reflection.createInstance(myResources
                        .getString("PieceClass"), image, size, new Point(480,
                        size.height / 2), myModel));
        }
    }

}
