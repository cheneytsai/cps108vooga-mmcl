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

    private static ResourceBundle myResources;
    private static Actor myNext;
    private GameModel myModel;

    public AddPiece(String gameName, GameModel model) {
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
            if (myNext == null) {
                myNext = createRandom();
                execute();

            } else {
                myModel.addActor(myNext);
                myNext = createRandom();
            }

        }
    }

    /**
     * Randomly creates a new actor from the resource file
     */
    private Actor createRandom() {
        String number = myResources.getString("NumberOfPieces");
        int random = myModel.getRandom().nextInt(Integer.parseInt(number));
        String image = myResources.getString("" + random);
        Image newImage = new ImageIcon(image).getImage();
        Dimension size = new Dimension(newImage.getWidth(null), newImage
                .getHeight(null));
        if (size.width % 52 == 0)
            return (Actor) Reflection.createInstance(myResources
                    .getString("PieceClass"), image, size, new Point(493,
                    size.height / 2), myModel);
        else
            return (Actor) Reflection.createInstance(myResources
                    .getString("PieceClass"), image, size, new Point(480,
                    size.height / 2), myModel);
    }
    
    public static Image nextImage(){
        return new ImageIcon(myNext.getImageString()).getImage();
    }

}
