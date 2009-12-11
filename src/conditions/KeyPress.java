package conditions;

import java.awt.event.KeyEvent;

import gameengine.GameEngine;


public class KeyPress implements Condition
{
    private GameEngine myEngine;
    private KeyEvent myLastKeyPressed;
    private Integer myExpectedKey;
    
    public KeyPress(String[] params, GameEngine engine)
    {
        myEngine = engine;
        myLastKeyPressed = null;
        myExpectedKey = Integer.parseInt(params[1]);

    }

    public boolean evaluate()
    {
        myLastKeyPressed = myEngine.getLastKeyPressed();
        if (myLastKeyPressed != null)
        {
           return (myLastKeyPressed.getKeyCode() == myExpectedKey);
        }
           return false;

    }


}
