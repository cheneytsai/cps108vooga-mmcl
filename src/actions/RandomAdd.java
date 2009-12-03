package actions;

import model.GameModel;
import actors.Actor;
import actors.BallPowerup;
import actors.EnlargePowerup;
import actors.ShrinkPowerup;

public class RandomAdd implements Action
{
    GameModel myModel;
    
    public RandomAdd(GameModel model)
    {
        myModel = model;
    }
    
    public void execute(Actor... actors)
    {
        int randomInt = myRand.nextInt(10);
        if(randomInt == 0)
        {
            new Add(myModel,BallPowerup.class.getCanonicalName()).execute(actors);
        }
        else if(randomInt == 1)
        {
            new Add(myModel,ShrinkPowerup.class.getCanonicalName()).execute(actors);
        }
        else if(randomInt == 2)
        {
            new Add(myModel,EnlargePowerup.class.getCanonicalName()).execute(actors);
        }
        
    }


}
