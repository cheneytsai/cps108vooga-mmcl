//TODO: Write this
package conditions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.GameModel;

import utilities.CollisionChecker;

import actions.Action;
import actors.Actor;
import actors.Brick;

public abstract class ConditionChecker {
    
    protected GameModel myModel;
    private List<Actor> myActors;
    protected Map<Condition, Action> myConditions;
    
    public ConditionChecker(GameModel model)
    {
        myModel = model;
        myConditions = new HashMap<Condition, Action>();
        loadConditions();
    }

    
    public void checkConditions() 
    {

        myActors = myModel.getActors();
        collisionCheck();
        conditionsCheck();
       
    }
    
    private void collisionCheck()
    {
        for (int k = 0; k < myActors.size(); k++)
        {
            if(myActors.get(k).hasMoved = true)
            {
              CollisionChecker.checkCollisions(myActors.get(k), myActors);  
            }
        }
    }
    
    private void conditionsCheck()
    {
        for (Condition c : myConditions.keySet())
        {
            if (c.evaluate())
                myConditions.get(c).execute();
        }
    }
    
    protected abstract void loadConditions();
   
    
    
    

}
