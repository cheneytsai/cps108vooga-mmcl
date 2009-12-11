package conditions;


import java.awt.Point;
import java.awt.event.MouseEvent;

import actors.Actor;

import utilities.CollisionChecker;

import gameengine.GameEngine;


public class MouseLocationOn implements Condition
{
    private GameEngine myEngine;
    private String myActor;
    private Point myPosition;
    
    public MouseLocationOn(String[] params, GameEngine engine)
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
               return CollisionChecker.intersects(a, myPosition);
           }
        }
           return false;

    }


}
