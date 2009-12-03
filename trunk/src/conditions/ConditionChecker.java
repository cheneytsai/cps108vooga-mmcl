//TODO: Write this
package conditions;

import java.util.List;

import model.GameModel;

import utilities.CollisionChecker;

import actors.Actor;
import actors.Brick;

public class ConditionChecker {
    
    private GameModel myModel;
    private List<Actor> myActors;
    public ConditionChecker(GameModel model)
    {
        myModel = model;
    }

    
    public void checkConditions() 
    {

        myActors = myModel.getActors();
        collisionCheck();
        

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
        
    }
    
    
    

}
