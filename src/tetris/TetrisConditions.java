package tetris;

import actors.Grid;
import conditions.ConditionChecker;
import model.GameModel;

public class TetrisConditions extends ConditionChecker {

    public TetrisConditions(GameModel model) {
        super(model);
    }

    @Override
    protected void loadConditions() {
        // TODO Write this

    }
    
    //TODO: this is static for testing purposes, but needs to be changed
    public static boolean isRowFull(int i){
        int numberInRow = 0;
        for (int j = 0; j < Grid.getGridSize().width; j++) {
            if (Grid.getState(j, i)) {
                numberInRow++;
            }
        }
        return numberInRow == Grid.getGridSize().width;
        
    }

}
