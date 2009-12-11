package dukeopalypse;

import gameengine.GameModel;

import java.awt.event.KeyEvent;

import view.Canvas;

public class DukeopalypseModel extends GameModel
{

    public DukeopalypseModel(String gameName,Canvas canvas)
    {
        super(gameName,canvas);
        myConditions = new DukeopalypseConditions(this);
    }

    protected void hotkeyCheck(String myLastKeyPressed)
    {

    }

    public void lose()
    {

    }

    public void update(KeyEvent myLastKeyPressed)
    {
        super.update(myLastKeyPressed);
    }
}
