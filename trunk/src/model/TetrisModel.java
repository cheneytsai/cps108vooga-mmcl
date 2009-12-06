package model;

import actions.AddPiece;
import actors.Grid;
import conditions.TetrisConditions;
import view.Canvas;

public class TetrisModel extends GameModel {

    private String myPreviousKey;

    public TetrisModel(Canvas canvas) {
        super(canvas);
        myConditions = new TetrisConditions(this);
        myPreviousKey = "";

    }

    public void update(String myLastKeyPressed) {
        if (myLastKeyPressed == null)
            super.update(null);
        else if (myPreviousKey.equals("W") && myLastKeyPressed.equals("W"))
            super.update(null);
        else {
            myPreviousKey = myLastKeyPressed;
            super.update(myLastKeyPressed);
        }
        
    }

    public void lose() {
        myCanvas.loadEnd("Lose");
    }
//TODO is this in conditions?
    public boolean gameOver() {
        for (int i = 0; i < Grid.getGridSize().width; i++) {
            if (Grid.getState(i, 0)) {
                return true;
            }
        }
        return false;
    }
    
    protected void initializeActors(){
        super.initializeActors();
        new AddPiece(myCanvas.getGameName(), this).execute();
    }

}
