package actions;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ResourceBundle;

import actors.Actor;
import actors.FallingPiece;

public class Rotate implements Action {
    
    private int myDegrees;
    private String myImage;
    private ResourceBundle myResources = ResourceBundle.getBundle("resources.TetrisRotation");
    
    
    public Rotate(){
//        myDegrees = degrees;

    }

    public void execute(Actor... actors) {
        String newImage = myResources.getString(((FallingPiece)actors[0]).getCurrentImageName());
        actors[0].setImage(newImage);
        ((FallingPiece)actors[0]).setCurrentImageName(newImage);
        int height = (int) actors[0].getSize().getHeight();
        int width = (int) actors[0].getSize().getWidth();
        actors[0].setSize(height, width);
//        actors[0].setImage(((FallingPiece)actors[0]).getCurrentImageName());
//        System.out.println("ROTATION!");
    }

}
