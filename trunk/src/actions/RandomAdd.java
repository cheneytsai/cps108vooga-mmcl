package actions;

import model.GameModel;
import actors.Actor;
import actors.BallPowerup;
import actors.BonusLevelPowerup;
import actors.EnlargePowerup;
import actors.ShrinkPowerup;

public class RandomAdd implements Action {
    GameModel myModel;

    public RandomAdd(GameModel model) {
        myModel = model;
    }

    public void execute(Actor... actors) {
        int randomInt = myModel.getRandom().nextInt(20);
        if (randomInt == 0 || randomInt == 1) {
            new Add(myModel, BallPowerup.class.getCanonicalName())
                    .execute(actors);
        } else if (randomInt == 2 || randomInt == 3) {
            new Add(myModel, ShrinkPowerup.class.getCanonicalName())
                    .execute(actors);
        } else if (randomInt == 4 || randomInt == 5) {
            new Add(myModel, EnlargePowerup.class.getCanonicalName())
                    .execute(actors);
        } else if (randomInt == 6) {
            if(myModel.getCanvas().getLevelNum() != 11)
            {
                new Add(myModel, BonusLevelPowerup.class.getCanonicalName())
                    .execute(actors);
            }
        }

    }

}
