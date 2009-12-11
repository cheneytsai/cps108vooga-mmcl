package cheney;


import java.awt.Point;
import conditions.Condition;


import actors.Actor;

import utilities.CollisionChecker;



public class MouseLocationOff implements Condition
{
    private GameEngine myEngine;
    private String myActor;
    private Point myPosition;
    
    public MouseLocationOff(String[] params, GameEngine engine)
    {
        myEngine = engine;
        myActor = params[1];
        myPosition = null;

    }

    public boolean evaluate()
    {
        myPosition = myEngine.getCanvas().getMousePosition();
        if (myPosition != null)
        {
           for(Actor a : myEngine.getActorsByID(myActor))
           {
               return !CollisionChecker.intersects(a, myPosition);
           }
        }
           return false;

    }


}
