package actions;

import model.GameModel;
import actors.Actor;

public class BonusLevel implements Action
{
    GameModel myModel;
    int myLevel;

    public BonusLevel(GameModel model,int level)
    {
        myModel = model;
        myLevel = level;
    }
    
    public void execute(Actor... actors)
    {
        myModel.loadBonusLevel(myLevel);

    }

}
