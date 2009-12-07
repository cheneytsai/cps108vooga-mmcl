package tetris;

import model.GameModel;
import actions.AddPiece;
import actors.Actor;
import actors.Grid;
import view.Canvas;

public class TetrisModel extends GameModel {

    private String myPreviousKey;
//    private String [] myPreviousKeys = {"",""};

    public TetrisModel(Canvas canvas) {
        super(canvas);
        myConditions = new TetrisConditions(this);
        myPreviousKey = "";


    }

    public void update(String myLastKeyPressed) {
        if (myLastKeyPressed == null){
            super.update(null);
//            myPreviousKeys[0] = ""; 
//            myPreviousKeys[1] = "";
        }
        else if (myPreviousKey.equals("W") && myLastKeyPressed.equals("W"))
//        else if (myPreviousKey.equals(myLastKeyPressed))
//        else if(myPreviousKeys[0].equals(myLastKeyPressed) && myPreviousKeys[1].equals("")){
//            myPreviousKeys[1] = myPreviousKeys[0];
//            myPreviousKeys[0] = myLastKeyPressed;
            super.update(null);
//        }
            
        else {
//            myPreviousKeys[1] = myPreviousKeys[0];
//            myPreviousKeys[0] = myLastKeyPressed;
            myPreviousKey = myLastKeyPressed;
            super.update(myLastKeyPressed);
        }
        
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
//        myPreviousKeys[0] = ""; 
//        myPreviousKeys[1] = "";
    }

}
