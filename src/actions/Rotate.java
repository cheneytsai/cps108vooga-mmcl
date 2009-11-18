package actions;

import java.awt.geom.AffineTransform;

import actors.Actor;

public class Rotate implements Action {
    
    private int myDegrees;
    
    public Rotate(int degrees){
        myDegrees = degrees;
    }

    public void execute(Actor... actors) {
      //creating the AffineTransform instance
        AffineTransform affineTransform = new AffineTransform();
        //rotate the image
        affineTransform.rotate(Math.toRadians(myDegrees*2*Math.PI/360));         
    }

}
