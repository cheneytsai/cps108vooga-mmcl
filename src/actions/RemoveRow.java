package actions;

import actors.Actor;
import actors.Grid;

public class RemoveRow implements Action {
    
    private int myRowToRemove;

    public RemoveRow(int i) {
        myRowToRemove = i;
    }

    public void execute(Actor... actors) {
        for(int i = 0; i<Grid.getGridSize().width; i++){
            
        }
    }

}
