package actions;

import java.util.ResourceBundle;

import utilities.CollisionChecker;
import actors.Actor;
import actors.Grid;

public class Replace implements Action {
    
//    private String myImageName;
//    private ResourceBundle myReplacements;

//    public Replace(String image, String gameName) {
//        myImageName = image;
//        myReplacements = ResourceBundle.getBundle("resources." + gameName + "Replacements");    

//    }

    public void execute(Actor... actors) {
        for(int i = 0; i<Grid.getGridSize().width; i++){
            for(int j = 0; j < Grid.getGridSize().height; j++){
                if(CollisionChecker.collide(actors[0], Grid.getMarker(i,j))){
                    Grid.addBlock(i,j);
                    
                }
            }
        }
        Grid.removeFullRowsAndDrop();
    }

}
