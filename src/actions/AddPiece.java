package actions;

import java.util.Random;
import java.util.ResourceBundle;

import actors.Actor;

public class AddPiece implements Action {
    
    private Random myRandom;
    private static ResourceBundle myResources;
    
    public AddPiece (String gameName){
        myRandom = new Random();
        if(myResources == null)
            myResources = ResourceBundle.getBundle("resources."+gameName+"Pieces");
    }

    public void execute(Actor... actors) {
        System.out.println("new " + myResources.getString(""+myRandom.nextInt(Integer.parseInt(myResources.getString("NumberOfPieces")))));
    }

}
