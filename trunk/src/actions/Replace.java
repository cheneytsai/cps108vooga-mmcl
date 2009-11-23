package actions;

import java.awt.Dimension;

import utilities.CollisionChecker;
import model.GameModel;
import actors.Actor;
import actors.Block;
import actors.Grid;
import actors.Marker;

public class Replace implements Action {

    private GameModel myModel;
    
    public Replace(GameModel model) {

        myModel = model;
    }

    public void execute(Actor... actors) {
        for(int i = 0; i<Grid.getMarkers().length; i++){
            for(Marker mark: Grid.getMarkers()[i]){
                if(CollisionChecker.collide(actors[0], mark)){
                    myModel.addActor(new Block("src/images/tetrisblock.gif", new Dimension(26,26), mark.getPosition(), myModel));
                }
            }
        }
        
    }

}
