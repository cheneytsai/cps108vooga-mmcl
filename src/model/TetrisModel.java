package model;

import conditions.TetrisConditions;
import view.Canvas;

public class TetrisModel extends GameModel {
    
    private String myPreviousKey;
    public TetrisModel(Canvas canvas) {
        super(canvas);
        myConditions = new TetrisConditions(this);
        myPreviousKey = "";

    }
    
    public void update(String myLastKeyPressed) 
    {
        if (myLastKeyPressed == null)
           super.update(null);
       else if (myPreviousKey.equals("W") && myLastKeyPressed.equals("W"))
            super.update(null);
        else
        {
            myPreviousKey = myLastKeyPressed;
            super.update(myLastKeyPressed);
        }
    }

}
