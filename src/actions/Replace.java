package actions;

import utilities.CollisionChecker;
import actors.Actor;
import actors.Grid;

/**
 * 
 * @author meganheysham
 *
 */
public class Replace implements Action {
    

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
