package tetris;

import java.awt.event.KeyEvent;
import model.GameModel;
import actions.AddPiece;
import actions.ChangeSpeed;
import actors.Actor;
import actors.Grid;
import view.Canvas;

public class TetrisModel extends GameModel {
    
    private int defaultKeyPressed = KeyEvent.VK_0;
    private int [] myPreviousKeys = {defaultKeyPressed,defaultKeyPressed};

    public TetrisModel(Canvas canvas) {
        super(canvas);
        myConditions = new TetrisConditions(this);
    }

    public void update(KeyEvent myLastKeyPressed) {
        if (gameOver()) {
            lose();
        }
        else if (myLastKeyPressed == null){
            myPreviousKeys[0] = defaultKeyPressed; 
            myPreviousKeys[1] = defaultKeyPressed;
            super.update(null);
        }
        else if (myPreviousKeys[0] == KeyEvent.VK_UP
                && myLastKeyPressed.getKeyCode() == KeyEvent.VK_UP)
        {
            super.update(null);
        }   
        else if (myLastKeyPressed.getKeyCode() == myPreviousKeys[0])
        {
            if(myPreviousKeys[0] == myPreviousKeys[1]){
                super.update(myLastKeyPressed);
            }
            else{
                myPreviousKeys[1] = myPreviousKeys[0];
                super.update(null);
                
            }
        }
        else{
            myPreviousKeys[1] = myPreviousKeys[0];
            myPreviousKeys[0] = myLastKeyPressed.getKeyCode();
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
        myPreviousKeys[0] = defaultKeyPressed; 
        myPreviousKeys[1] = defaultKeyPressed;
//        new ChangeSpeed(myCanvas.getLevelNum()).execute(actor);
    }

}
