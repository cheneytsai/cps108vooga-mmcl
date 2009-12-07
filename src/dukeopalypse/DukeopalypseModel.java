package dukeopalypse;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;

import model.GameModel;

import actions.Add;
import actions.Direction;
import actions.Update;
import actors.Actor;
import actors.Ball;
import actors.Paddle;
import actors.PhysicsVector;
import view.Canvas;

public class DukeopalypseModel extends GameModel {

    public DukeopalypseModel(Canvas canvas) {
        super(canvas);
        myConditions = new DukeopalypseConditions(this);
    }

    protected void hotkeyCheck(String myLastKeyPressed)
    {


    }
    
    public void lose() {
       
    }
    
    public void update(KeyEvent myLastKeyPressed)
    {
        super.update(myLastKeyPressed);
    }
}
