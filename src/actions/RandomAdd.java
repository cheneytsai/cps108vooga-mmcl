package actions;

import gameengine.GameModel;
import actors.Actor;
import actors.BallPowerup;
import actors.EnlargePowerup;
import actors.ShrinkPowerup;

public class RandomAdd implements Action
{
    private GameModel myModel;

    public RandomAdd(GameModel model)
    {
        myModel = model;
    }

    public void execute(Actor... actors)
    {
        int randomInt = myModel.getRandom().nextInt(10);
        if (randomInt == 0)
        {
            new Add(myModel, BallPowerup.class.getCanonicalName())
                    .execute(actors);
        } else if (randomInt == 1)
        {
            new Add(myModel, ShrinkPowerup.class.getCanonicalName())
                    .execute(actors);
        } else if (randomInt == 2)
        {
            new Add(myModel, EnlargePowerup.class.getCanonicalName())
                    .execute(actors);
        }
        // else if (randomInt == 6) {
        // if(myModel.getCanvas().getLevelNum() != 11)
        // {
        // new Add(myModel, BonusLevelPowerup.class.getCanonicalName())
        // .execute(actors);
        // }
        // }

    }

}
