package actions;

import java.awt.Point;
import java.util.ResourceBundle;

import actors.Actor;
import actors.FallingPiece;


public class Rotate implements Action {
    
    private ResourceBundle myRotations;

    public Rotate(String gameName) {
       myRotations = ResourceBundle.getBundle("resources." + gameName + "Rotation");    
   }

    public void execute(Actor... actors) {
        String newImage = myRotations.getString(((FallingPiece)actors[0]).getCurrentImageName());
        actors[0].setImage(newImage);
        ((FallingPiece)actors[0]).setCurrentImageName(newImage);
        int height = (int) actors[0].getSize().getHeight();
        int width = (int) actors[0].getSize().getWidth();
        actors[0].setSize(height, width);
        if(height>width)
            actors[0].setPosition(new Point(actors[0].getPosition().x+13,actors[0].getPosition().y));
        if(height<width)
            actors[0].setPosition(new Point(actors[0].getPosition().x-13,actors[0].getPosition().y));
//        actors[0].setImage(((FallingPiece)actors[0]).getCurrentImageName());
//        System.out.println("ROTATION!");
    }

}
