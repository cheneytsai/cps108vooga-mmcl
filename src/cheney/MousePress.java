package cheney;


import java.awt.event.MouseEvent;
import conditions.Condition;



public class MousePress implements Condition
{
    private GameEngine myEngine;
    private MouseEvent myLastMouseClick;
    private Integer myExpectedKey;
    
    public MousePress(String[] params, GameEngine engine)
    {
        myEngine = engine;
        myLastMouseClick = null;
        myExpectedKey = Integer.parseInt(params[1]);

    }

    public boolean evaluate()
    {
        myLastMouseClick = myEngine.getLastMouseEvent();
        if (myLastMouseClick != null)
        {
           return (myLastMouseClick.getID() == myExpectedKey);
        }
           return false;

    }


}
