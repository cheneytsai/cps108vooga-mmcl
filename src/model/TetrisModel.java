package model;

import actors.Grid;
import conditions.TetrisConditions;
import view.Canvas;
import view.EndView;

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
        new EndView("Lose","Tetris",myCanvas.getScore(),myCanvas);
    }

    public boolean gameOver() {
        for (int i = 0; i < Grid.getGridSize().width; i++) {
            if (Grid.getState(i, 0)) {
                return true;
            }
        }
        return false;
    }

}
