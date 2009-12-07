package tetris;

import model.GameModel;
import actions.AddPiece;
import actors.Actor;
import actors.Grid;
import view.Canvas;

public class TetrisModel extends GameModel {

    private String [] myPreviousKeys = {"",""};

    public TetrisModel(Canvas canvas) {
        super(canvas);
        myConditions = new TetrisConditions(this);


    }

    public void update(String myLastKeyPressed) {
        if (myLastKeyPressed == null){
            myPreviousKeys[0] = ""; 
            myPreviousKeys[1] = "";
            super.update(null);
        }
        else if (myPreviousKeys[0].equals("W") && myLastKeyPressed.equals("W"))

            super.update(null);
            
        else if (myLastKeyPressed.equals(myPreviousKeys[0]))
        {
            if(myPreviousKeys[0].equals(myPreviousKeys[1])){
                super.update(myLastKeyPressed);
            }
            else{
                myPreviousKeys[1] = myPreviousKeys[0];
                super.update(null);
                
            }
        }
        else{
            myPreviousKeys[1] = myPreviousKeys[0];
            myPreviousKeys[0] = myLastKeyPressed;
            super.update(myLastKeyPressed);
        }

        
//        else {
//            myPreviousKey = myLastKeyPressed;
//            super.update(myLastKeyPressed);
//        }
        
    }

    public void lose() {
        myCanvas.loadEnd("Lose");
    }
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
    
    public void addActor(Actor actor){
        super.addActor(actor);
        myPreviousKeys[0] = ""; 
        myPreviousKeys[1] = "";
    }

}
