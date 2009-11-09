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
    protected Map<String, Action> myKeyEvents;
    protected Map<Actor, Action> myInteractions;
    protected Action myDefaultBehavior;
    
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
    
    public void setPosition(Point p)
    {
        myPosition = p;
    }
    
    public void setVelocity(PhysicsVector v)
    {
        myVelocity = v;
    }
    
    public Point getPosition()
    {
        return myPosition;
    }
    
    public PhysicsVector getVelocity()
    {
        return myVelocity;
    }
    
    public String getImage()
    {
        return myImage;
    }
    
    public void setImage(String newImage)
    {
        myImage = newImage;
    }
    
    public void remove()
    {
        myModel.remove(this);
    }
    
    
}
