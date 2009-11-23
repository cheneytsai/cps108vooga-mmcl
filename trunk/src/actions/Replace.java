package actions;

import java.awt.Dimension;
import java.awt.Point;

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
        for(int i = 0; i<Grid.getGridSize().width; i++){
            for(int j = 0; j < Grid.getGridSize().height; j++){
                if(CollisionChecker.collide(actors[0], Grid.getMarker(i,j))){
                    Grid.addBlock(i,j);
                    myModel.addActor(new Block("src/images/tetrisblock.gif", new Dimension(26,26), Grid.getMarker(i, j).getPosition(), myModel));
                }
            }
        }
        
//        for(int i = Grid.getGridSize().height-1;i>0;i++)
//        {
//        Marker mark = new Marker("src/images/brick6.gif", new Dimension(Grid.getGridSize().width*26,1), new Point(480,(Grid.getGridSize().height*i)*26-13), myModel);
//            int count = 0;
//            for(Actor a: myModel.getActors()){
//                if(CollisionChecker.collide(mark, a)){
//                    count++;
//                }
//            }
//            if(count==Grid.getGridSize().width){
////                new RemoveRow(i);
//            }
//        }
//        for(int i = Grid.getGridSize().height - 1; i>0; i--){
//        
//            int count = 0;
//            for(int j = 0; j < Grid.getGridSize().width; j++){
//                if(Grid.getState(j,i)){
//                    count++;
//                }
//            }
//            if(count==Grid.getGridSize().width)
//                new RemoveRow(i).execute();
//        }
        
    }

}
