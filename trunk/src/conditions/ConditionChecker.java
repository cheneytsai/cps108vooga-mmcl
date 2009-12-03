//TODO: Write this
package conditions;

import java.util.List;

import model.GameModel;

import utilities.CollisionChecker;

import actors.Actor;
import actors.Brick;

public class ConditionChecker {
    
    static List<Actor> myActors;

    
    public static void checkConditions(List<Actor> actors, GameModel model) 
    {
       /* if (Brick.getNumberOfBricks() == 0)
        {
            model.clearActors();
            JOptionPane.showMessageDialog(model.myCanvas, "You win", "Game Over", 0);
        }
        */
        int numBrick = Brick.getNumberOfBricks();
       // myActors = actors;
        
//        List<Actor> movedActors;
        for (int k = 0; k < actors.size(); k++)
        {
            if(actors.get(k).hasMoved = true)
            {
              CollisionChecker.checkCollisions(actors.get(k), actors);  
            }
        }
        
        if (Brick.getNumberOfBricks() < numBrick)
        {

        }

        
        //Check Conditions....includes Collisions
    }
    

}
