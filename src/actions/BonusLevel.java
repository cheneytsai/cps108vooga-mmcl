package actions;

import model.GameModel;
import actors.Actor;

public class BonusLevel implements Action
{
    GameModel myModel;
    int bestBonusLevel = 11;

    public BonusLevel(GameModel model)
    {
        myModel = model;
    }
    
    @Override
    public void execute(Actor... actors)
    {
        myModel.loadBonusLevel(bestBonusLevel);

    }

}
