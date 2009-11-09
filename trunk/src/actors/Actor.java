package actors;
import java.awt.Point;
import java.util.*;

import actions.*;
import model.*;

public abstract class Actor {
    
    private GameModel myModel;
    private String myImage;
    private Point myPosition;    
    private PhysicsVector myVelocity;
    private PhysicsVector myAcceleration;
    private Map<String, Action> myKeyEvents;
    private Map<Actor, Action> myInteractions;
    private Action myDefaultBehavior;
    
    public Actor(String image, Point position, GameModel model)
    {
        myImage = image;
        myPosition = position;
        myModel = model;
        myVelocity = new PhysicsVector(new Direction(0, 0), 0);
        myAcceleration = new PhysicsVector(new Direction(0, 0), 0);
        loadBehavior();
    }
    
    protected abstract void loadBehavior();
    
    public void act(List<String> keysPressed)
    {
        for (String s : myKeyEvents.keySet())
        {
            if (keysPressed.contains(s))
                myKeyEvents.get(s).execute(this);
        }
        
        if (myDefaultBehavior != null)
            myDefaultBehavior.execute(this);      
        
    }
    
    public void remove()
    {
        myModel.remove(this);
    }
    
    
}
